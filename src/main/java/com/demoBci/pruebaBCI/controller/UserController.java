package com.demoBci.pruebaBCI.controller;

import com.demoBci.pruebaBCI.dto.MensajeApi;
import com.demoBci.pruebaBCI.dto.UsuarioAutenticar;
import com.demoBci.pruebaBCI.dto.UsuariosDto;
import com.demoBci.pruebaBCI.entity.Usuario;
import com.demoBci.pruebaBCI.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api/v1/usuario")
public class UserController {
    @Autowired
    IuserService iuserService;
    @PostMapping(value="/crear")
    public ResponseEntity<MensajeApi> crearUsuario(@RequestBody UsuariosDto usuario) {
        return iuserService.crear(usuario);
    }

        /*@PostMapping(value="/autenticar")
        public ResponseEntity<MensajeApi> autenticarUsuario(@RequestBody UsuarioAutenticar usuarioAutenticar) {
            return ResponseEntity.ok(iuserService.autenticar(usuarioAutenticar));
        }*/




}
