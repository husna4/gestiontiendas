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

    public Trabajador getTrabajador(Long id);

    public List<Trabajador> getTrabajadoresByCodigoTienda(String codigoTienda);

    public Trabajador crear(String codigoTienda, Trabajador trabajador);

    public Trabajador editar(Long id, TrabajadorRequestDto dto);

    public void eliminar(Long id);
}
