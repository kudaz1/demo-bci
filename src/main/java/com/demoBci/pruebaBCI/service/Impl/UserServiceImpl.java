package com.demoBci.pruebaBCI.service.Impl;

import com.demoBci.pruebaBCI.config.JwtService;
import com.demoBci.pruebaBCI.dao.TelefonosDao;
import com.demoBci.pruebaBCI.dao.UsuarioDao;
import com.demoBci.pruebaBCI.dto.*;
import com.demoBci.pruebaBCI.entity.Role;
import com.demoBci.pruebaBCI.entity.Telefonos;
import com.demoBci.pruebaBCI.entity.Usuario;
import com.demoBci.pruebaBCI.service.IuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IuserService {
    @Autowired
    UsuarioDao usuarioDao;
    @Autowired
    TelefonosDao telefonosDao;

    private final UsuarioDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${password.regex.regexp}")
    private String passwordRegex;

    @Value("${email.regex.regexp}")
    private String emailRegex;

    public ResponseEntity<MensajeApi> crear(UsuariosDto usuariosDto){

        Telefonos telefonos = new Telefonos();
        MensajeApi mensajeApi = new MensajeApi();
        UsuarioApi usuarioApi = new UsuarioApi();

        try {

            boolean validarCorreo = ValidarEmail(usuariosDto.getEmail());
            boolean validarPassword = ValidarContraseña(usuariosDto.getPassword());

            if(!validarCorreo){
                mensajeApi.setMensaje("El correo ingresado es inválido.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeApi);
            }

            if(!validarPassword){
                mensajeApi.setMensaje("la contraseña no tiene el formato correcto.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeApi);
            }

            Date fechaCreado = new Date();

            Optional<Usuario>  usuarioConsultar = usuarioDao.findByEmail(usuariosDto.getEmail());

            if(usuarioConsultar.isPresent()){
                mensajeApi.setMensaje("El correo ya registrado");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mensajeApi);
            }

            var usuario = Usuario.builder()
                    .name(usuariosDto.getName())
                    .email(usuariosDto.getEmail())
                    .password(passwordEncoder.encode(usuariosDto.getPassword()))
                    .created(fechaCreado)
                    .last_login(fechaCreado)
                    .isactive(true)
                    .role(Role.ADMIN)
                    .build();

            repository.save(usuario);

            for (TelefonosDto telefono : usuariosDto.getPhones()) {
                telefonos.setNumber(telefono.getNumber());
                telefonos.setCitycode(telefono.getCitycode());
                telefonos.setContrycode(telefono.getContrycode());
                telefonos.setUserId(usuario.getId().toString());

                telefonosDao.save(telefonos);
            }

            var jwtToken = jwtService.generateToken(usuario);

            Optional<Usuario> usuarioBaseDatos = usuarioDao.findById(usuario.getId());

            Usuario usuarioToken = usuarioBaseDatos.get();

            usuarioToken.setToken(jwtToken);

            repository.save(usuarioToken);

            usuarioApi.setName(usuario.getName());
            usuarioApi.setEmail(usuario.getEmail());
            usuarioApi.setPassword(usuario.getPassword());
            usuarioApi.setPhones(usuariosDto.getPhones());
            usuarioApi.setId(usuario.getId().toString());
            usuarioApi.setCreated(usuario.getCreated());
            usuarioApi.setModified(usuario.getModified());
            usuarioApi.setLast_login(usuario.getLast_login());
            usuarioApi.setToken(usuarioToken.getToken());
            usuarioApi.setIsactive(usuario.getIsactive());
            usuarioApi.setMensaje("Usuario Creado");

            return ResponseEntity.ok(usuarioApi);
        }catch (Exception e){
            return null;
        }


    }



    public ResponseEntity<MensajeApi> actualizar(UsuariosDto usuariosDto, String idUsuario, String token){

        try {

            MensajeApi mensajeApi = new MensajeApi();
            UsuarioApi usuarioApi = new UsuarioApi();

            String tokenBearer = token.substring(7);

            Date fechaModificacion = new Date();

            UUID uid = UUID.fromString(idUsuario);

            Optional<Usuario> usuarioBaseDatos = usuarioDao.findById(uid);


            if(usuarioBaseDatos.isPresent()){
                Usuario usuarioActualizado= usuarioBaseDatos.get();

                if(!tokenBearer.equals(usuarioActualizado.getToken())) {
                    mensajeApi.setMensaje("El token ingresado no esta autorizado.");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mensajeApi);
                }

                Optional<Usuario> correoBaseDatos = usuarioDao.findByEmail(usuariosDto.getEmail());

                if(correoBaseDatos.isPresent() ){

                    Usuario correoEncontrado = correoBaseDatos.get();

                    if(!Objects.equals(correoEncontrado.getId().toString(), usuarioActualizado.getId().toString())){
                        mensajeApi.setMensaje("El correo ingresado ya se encuentra registrado.");
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mensajeApi);
                    }

                }

                boolean validarCorreo = ValidarEmail(usuariosDto.getEmail());

                if(!validarCorreo){
                    mensajeApi.setMensaje("El correo no tiene el formato correcto.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeApi);
                }

                boolean validarPassword = ValidarContraseña(usuariosDto.getPassword());

                if(!validarPassword){
                    mensajeApi.setMensaje("la contraseña no tiene el formato correcto.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeApi);
                }

                usuarioActualizado.setName(usuariosDto.getName());
                usuarioActualizado.setEmail(usuariosDto.getEmail());
                usuarioActualizado.setPassword(passwordEncoder.encode(usuariosDto.getPassword()));
                usuarioActualizado.setModified(fechaModificacion);
                usuarioActualizado.setLast_login(fechaModificacion);

                repository.save(usuarioActualizado);

                List<Telefonos> telefonosLista = telefonosDao.findByUserId(idUsuario);

                for (Telefonos telefono : telefonosLista) {

                    telefonosDao.deleteById(telefono.getId());
                }

                Telefonos telefonos = new Telefonos();

                for (TelefonosDto telefono : usuariosDto.getPhones()) {
                    telefonos.setNumber(telefono.getNumber());
                    telefonos.setCitycode(telefono.getCitycode());
                    telefonos.setContrycode(telefono.getContrycode());
                    telefonos.setUserId(idUsuario);


                    telefonosDao.save(telefonos);
                }

                var jwtToken = jwtService.generateToken(usuarioActualizado);

                usuarioApi.setName(usuarioActualizado.getName());
                usuarioApi.setEmail(usuarioActualizado.getEmail());
                usuarioApi.setPassword(usuarioActualizado.getPassword());
                usuarioApi.setPhones(usuariosDto.getPhones());
                usuarioApi.setId(usuarioActualizado.getId().toString());
                usuarioApi.setCreated(usuarioActualizado.getCreated());
                usuarioApi.setModified(usuarioActualizado.getModified());
                usuarioApi.setLast_login(usuarioActualizado.getLast_login());
                usuarioApi.setToken(jwtToken);
                usuarioApi.setIsactive(usuarioActualizado.getIsactive());
                usuarioApi.setMensaje("Usuario Actualizado");

            }else{
                mensajeApi.setMensaje("Usuario no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeApi);
            }
            return ResponseEntity.ok(usuarioApi);

        }catch (Exception e) {

            return null;

        }

    }

    public boolean ValidarEmail(String email) {

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean ValidarContraseña(String password) {

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
