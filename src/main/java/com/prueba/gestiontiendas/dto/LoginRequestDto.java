package com.prueba.gestiontiendas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Husnain
 */

@Data
public class LoginRequestDto {
    @NotBlank(message = "{auth.username.obligatorio}")
    private String username;

    @NotBlank(message = "{auth.password.obligatorio}")
    private String password;
}
