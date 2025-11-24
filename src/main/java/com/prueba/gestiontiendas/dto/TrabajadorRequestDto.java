package com.prueba.gestiontiendas.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MINIMO_HORAS_DISPONIBLES_TRABAJADOR;

/**
 * @author Husnain
 */

@Getter
@Setter
public class TrabajadorRequestDto {

    @NotBlank(message = "{trabajador.nombre.obligatorio}")
    @Size(max = 50, message = "El nombre debe tener menos de 50 caracteres")
    private String nombre;

    @NotBlank(message = "{trabajador.apellidos.obligatorio}")
    @Size(max = 50, message = "{trabajador.apellidos.tamano}")
    private String apellidos;

    @NotBlank(message = "{trabajador.dni.obligatorio}")
    @Size(min = 9, max = 9, message = "{trabajador.dni.tamano}")
    private String dni;

    @NotNull(message = "{trabajador.horas.obligatorio}")
    @Min(value = MINIMO_HORAS_DISPONIBLES_TRABAJADOR, message = "{trabajador.horas.min}")
    @Max(value = MAXIMO_HORAS_DISPONIBLES_TRABAJADOR, message = "{trabajador.horas.max}")
    private Integer horasDisponibles;
}
