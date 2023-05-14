package com.demoBci.pruebaBCI.controller;

import com.demoBci.pruebaBCI.dto.MessageApiDto;
import com.demoBci.pruebaBCI.dto.UserDto;

import com.demoBci.pruebaBCI.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    IuserService iuserService;
    @PostMapping(value="/create")
    public ResponseEntity<MessageApiDto> createUser(@RequestBody UserDto userDto) {
        return iuserService.create(userDto);
    }






}
