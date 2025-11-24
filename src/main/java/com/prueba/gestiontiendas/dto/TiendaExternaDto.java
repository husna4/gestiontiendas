package com.prueba.gestiontiendas.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Husnain
 */
@Getter
@Setter
public class TiendaExternaDto {
    private Long id;
    private String description;
    private String address;
    private String city;
}
