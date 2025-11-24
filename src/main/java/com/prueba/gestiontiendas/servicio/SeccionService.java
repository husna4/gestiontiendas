package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.modelo.Seccion;

import java.util.List;

/**
 * @author Husnain
 */

public interface SeccionService {
    public Seccion getSeccion(Long id);
    public List<Seccion> getSecciones();
}
