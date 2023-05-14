package com.demoBci.pruebaBCI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneDto {
    private long id;
    private String number;
    private String citycode;
    private String contrycode;
}
