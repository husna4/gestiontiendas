package com.prueba.gestiontiendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Husnain
 */

@Builder
@Getter
@Setter
public class SeccionEstadoDto {
    private String nombre;
    private int horasTrabajoNecesarias;
    private List<TrabajadorSeccionDto> trabajadores;
}
