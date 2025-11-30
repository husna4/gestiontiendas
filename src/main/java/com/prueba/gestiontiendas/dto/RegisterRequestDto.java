package com.prueba.gestiontiendas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Husnain
 */

@Data
public class RegisterRequestDto {

    @NotBlank(message = "{auth.username.obligatorio}")
    @Size(min = 3, max = 50, message = "{auth.username.tamano}")
    private String username;

    @NotBlank(message = "{auth.password.obligatorio}")
    @Size(min = 6, message = "{auth.password.tamano}")
    private String password;
}
