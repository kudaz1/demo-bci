package com.demoBci.pruebaBCI.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserApiDto extends MessageApiDto {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private String Id;
    private Date created;
    private Date modified;
    private Date last_login;
    private String token;
    private Boolean isactive;
}
