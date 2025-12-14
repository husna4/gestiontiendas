package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;

import java.util.List;

/**
 * @author Husnain
 */
public interface AsignacionTrabajadorSeccionService {

    AsignacionTrabajadorSeccion asignar(Long trabajadorId, Long seccionId, Integer horas);
    void desasignar(Long trabajadorId, Long seccionId);
    List<AsignacionTrabajadorSeccion> getAsignacionesTrabajador(Long trabajadorId);
}
