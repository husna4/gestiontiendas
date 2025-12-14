package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.modelo.Seccion;

import java.util.List;

/**
 * @author Husnain
 */

public interface SeccionService {
    Seccion getSeccion(Long id);
    List<Seccion> getSecciones();
}
