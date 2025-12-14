package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.dto.InformeEstadoTiendaDto;
import com.prueba.gestiontiendas.dto.InformeHorasSinCubrirDto;

/**
 * @author Husnain
 */
public interface InformeService {

    InformeEstadoTiendaDto getInformeEstadoTienda(String codigoTienda);
    InformeHorasSinCubrirDto getInformeHorasSinCubrir(String codigoTienda);
}
