package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.modelo.Seccion;
import com.prueba.gestiontiendas.repositorio.SeccionRepository;
import com.prueba.gestiontiendas.servicio.SeccionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Husnain
 */

@Service
public class SeccionServiceImpl implements SeccionService {
    private final SeccionRepository seccionRepository;

    public SeccionServiceImpl(SeccionRepository seccionRepository) {
        this.seccionRepository = seccionRepository;
    }

    public Seccion getSeccion(Long id) {
        return seccionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Seccion.class.getSimpleName(), "id", String.valueOf(id)));
    }

    public List<Seccion> getSecciones(){
        return seccionRepository.findAll();
    }
}
