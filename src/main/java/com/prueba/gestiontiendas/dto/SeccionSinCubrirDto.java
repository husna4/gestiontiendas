package com.prueba.gestiontiendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Husnain
 */

@Builder
@Getter
@Setter
public class SeccionSinCubrirDto {
    private String nombre;
    private int horasNecesarias;
    private int horasCubiertas;
    private int horasSinCubrir;
}
