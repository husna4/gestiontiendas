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
public class InformeEstadoTiendaDto {
    private String nombreTienda;
    private String direccion;
    private String ciudad;
    private List<SeccionEstadoDto> seccionesEstadoDto;

}
