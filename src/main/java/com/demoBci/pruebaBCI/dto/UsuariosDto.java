package com.demoBci.pruebaBCI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuariosDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<TelefonosDto> phones;


}
