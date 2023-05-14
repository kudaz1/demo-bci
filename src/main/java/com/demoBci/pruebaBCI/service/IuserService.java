package com.demoBci.pruebaBCI.service;

import com.demoBci.pruebaBCI.dto.MessageApiDto;
import com.demoBci.pruebaBCI.dto.UserDto;
import org.springframework.http.ResponseEntity;


public interface IuserService {
    ResponseEntity<MessageApiDto> create(UserDto userDto);

    ResponseEntity<MessageApiDto> update(UserDto userDto, String userID, String token);
}
