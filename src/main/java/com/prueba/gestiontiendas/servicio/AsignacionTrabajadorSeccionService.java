package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;

import java.util.List;

/**
 * @author Husnain
 */
public interface AsignacionTrabajadorSeccionService {

    public AsignacionTrabajadorSeccion asignar(Long trabajadorId, Long seccionId, Integer horas);
    public void desasignar(Long trabajadorId, Long seccionId);
    public List<AsignacionTrabajadorSeccion> getAsignacionesTrabajador(Long trabajadorId);
}
