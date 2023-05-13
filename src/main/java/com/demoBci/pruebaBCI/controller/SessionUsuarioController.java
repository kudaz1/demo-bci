package com.demoBci.pruebaBCI.controller;

import com.demoBci.pruebaBCI.dto.MensajeApi;
import com.demoBci.pruebaBCI.dto.UsuariosDto;
import com.demoBci.pruebaBCI.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/login")
public class SessionUsuarioController {

    @Autowired
    IuserService iuserService;
    @GetMapping
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Bienvenido al Sistema");
    }

    @PutMapping(value = "/{idUser}/actualizar")
    public ResponseEntity<MensajeApi> actualizarUsuario(@RequestBody UsuariosDto usuario, @PathVariable("idUser") String idUsuario, @RequestHeader(HttpHeaders.AUTHORIZATION) String token ) {

        return iuserService.actualizar(usuario, idUsuario, token);
    }
}
