package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.modelo.Tienda;
import com.prueba.gestiontiendas.repositorio.TiendaRepository;
import com.prueba.gestiontiendas.servicio.TiendaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Husnain
 */

@Service
@Transactional(readOnly = true)
public class TiendaServiceImpl implements TiendaService {

    private final TiendaRepository tiendaRepository;

    public TiendaServiceImpl(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    @Override
    public Tienda getTiendaByCodigo(String codigo) {
        return tiendaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Tienda.class.getSimpleName(), "codigo", codigo));
    }
}
