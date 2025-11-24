package com.prueba.gestiontiendas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MINIMO_HORAS_DISPONIBLES_TRABAJADOR;

/**
 * @author Husnain
 */

@Setter
@Getter
public class AsignacionRequestDto {
    @NotNull(message = "{asignacion.seccion.obligatorio}")
    private Long seccionId;

    @NotNull(message = "{asignacion.horas.obligatorio}")
    @Min(value = MINIMO_HORAS_DISPONIBLES_TRABAJADOR, message = "{asignacion.horas.min}")
    @Max(value = MAXIMO_HORAS_DISPONIBLES_TRABAJADOR, message = "{asignacion.horas.max}")
    private Integer horasAsignadas;
}
