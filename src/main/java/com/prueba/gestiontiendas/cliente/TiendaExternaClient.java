package com.prueba.gestiontiendas.cliente;

import com.prueba.gestiontiendas.dto.TiendaExternaDto;

import java.util.Optional;

/**
 * @author Husnain
 */
public interface TiendaExternaClient {
    Optional<TiendaExternaDto> getTienda(String codigoTienda);
}
