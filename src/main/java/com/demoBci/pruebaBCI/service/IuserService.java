package com.demoBci.pruebaBCI.service;

import com.demoBci.pruebaBCI.dto.MensajeApi;
import com.demoBci.pruebaBCI.dto.UsuariosDto;
import org.springframework.http.ResponseEntity;


public interface IuserService {
    ResponseEntity<MensajeApi> crear(UsuariosDto usuario);
    /*MensajeApi autenticar(UsuarioAutenticar usuarioAutenticar);*/
    ResponseEntity<MensajeApi> actualizar(UsuariosDto usuario, String idUsuario, String token);
}
