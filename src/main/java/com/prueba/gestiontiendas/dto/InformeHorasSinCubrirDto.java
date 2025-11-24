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
public class InformeHorasSinCubrirDto {
    private String nombreTienda;
    private String direccion;
    private String ciudad;
    private List<SeccionSinCubrirDto> seccionesSinCubrir;
}
