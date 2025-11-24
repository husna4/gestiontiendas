package com.prueba.gestiontiendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Husnain
 */

@Getter
@Setter
@Builder
public class AsignacionResponseDto {
    private Long id;
    private Long trabajadorId;
    private String nombreTrabajador;
    private Long seccionId;
    private String nombreSeccion;
    private Integer horasAsignadas;
}
