package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.dto.InformeEstadoTiendaDto;
import com.prueba.gestiontiendas.dto.InformeHorasSinCubrirDto;

/**
 * @author Husnain
 */
public interface InformeService {

    public InformeEstadoTiendaDto getInformeEstadoTienda(String codigoTienda);
    public InformeHorasSinCubrirDto getInformeHorasSinCubrir(String codigoTienda);
}
