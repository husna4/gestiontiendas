package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.dto.TrabajadorRequestDto;
import com.prueba.gestiontiendas.modelo.Trabajador;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Husnain
 */

@Service
public interface TrabajadorService {

    Trabajador getTrabajador(Long id);

    List<Trabajador> getTrabajadoresByCodigoTienda(String codigoTienda);

    Trabajador crear(String codigoTienda, Trabajador trabajador);

    Trabajador editar(Long id, TrabajadorRequestDto dto);

    void eliminar(Long id);
}
