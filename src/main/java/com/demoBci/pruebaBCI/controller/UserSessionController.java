package com.demoBci.pruebaBCI.controller;

import com.demoBci.pruebaBCI.dto.MessageApiDto;
import com.demoBci.pruebaBCI.dto.UserDto;
import com.demoBci.pruebaBCI.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/userSession")
public class UserSessionController {

    @Autowired
    IuserService iuserService;
    @GetMapping
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Bienvenido al Sistema");
    }

    @PutMapping(value = "/{idUser}/update")
    public ResponseEntity<MessageApiDto> actualizarUsuario(@RequestBody UserDto userDto, @PathVariable("idUser") String userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token ) {

        return iuserService.update(userDto, userId, token);
    }
}
