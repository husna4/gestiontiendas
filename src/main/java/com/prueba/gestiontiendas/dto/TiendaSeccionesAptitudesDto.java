package com.prueba.gestiontiendas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Husnain
 */

@Getter
@Setter
public class TiendaSeccionesAptitudesDto {
    private String codigoTienda;
    private String nombre;
    private List<SeccionAptitudesDto> secciones;
}
