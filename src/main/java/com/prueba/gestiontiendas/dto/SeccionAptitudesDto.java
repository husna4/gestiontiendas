package com.prueba.gestiontiendas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Husnain
 */

@Getter
@Setter
public class SeccionAptitudesDto {
    private Long id;
    private String nombre;
    private Integer horasTrabajoDiarias;
    private List<AptitudDto> aptitudes;
}
