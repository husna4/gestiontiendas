package com.prueba.gestiontiendas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Husnain
 */

@Data
public class CambiarPasswordDto {

    @NotBlank(message = "{auth.cambiarPassword.actual.obligatorio}")
    private String actualPassword;

    @NotBlank
    @Size(min = 6, message = "{auth.password.tamano}")
    private String nuevoPassword;
}
