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
public class TrabajadorSeccionDto {
    private String nombre;
    private String apellidos;
    private String dni;
    private int horasAsignadas;
}
