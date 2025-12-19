package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.dto.TrabajadorRequestDto;
import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.modelo.Tienda;
import com.prueba.gestiontiendas.modelo.Trabajador;
import com.prueba.gestiontiendas.repositorio.TrabajadorRepository;
import com.prueba.gestiontiendas.servicio.TiendaService;
import com.prueba.gestiontiendas.servicio.TrabajadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Husnain
 */

@Service
@Transactional(readOnly = true)
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final TiendaService tiendaService;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository,
                                 TiendaService tiendaService) {
        this.trabajadorRepository = trabajadorRepository;
        this.tiendaService = tiendaService;
    }

    @Override
    public Trabajador getTrabajador(Long id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        Trabajador.class.getSimpleName(), "id", String.valueOf(id)));
    }

    @Override
    public List<Trabajador> getTrabajadoresByCodigoTienda(String codigoTienda) {
        return trabajadorRepository.findByTiendaCodigo(codigoTienda);
    }

    @Override
    @Transactional
    public Trabajador crear(String codigoTienda, Trabajador trabajador) {
        Tienda tienda = tiendaService.getTiendaByCodigo(codigoTienda);

        trabajador.setTienda(tienda);
        return trabajadorRepository.save(trabajador);
    }

    @Override
    @Transactional
    public Trabajador editar(Long id, TrabajadorRequestDto dto) {
        Trabajador trabajador = getTrabajador(id);

        trabajador.setNombre(dto.getNombre());
        trabajador.setApellidos(dto.getApellidos());
        trabajador.setDni(dto.getDni());
        trabajador.setHorasDisponibles(dto.getHorasDisponibles());

        return trabajadorRepository.save(trabajador);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        getTrabajador(id);
        trabajadorRepository.deleteById(id);
    }
}
