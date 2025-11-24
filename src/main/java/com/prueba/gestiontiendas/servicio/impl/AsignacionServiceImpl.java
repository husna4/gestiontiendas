package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.excepciones.HorasInsuficientesException;
import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import com.prueba.gestiontiendas.modelo.Seccion;
import com.prueba.gestiontiendas.modelo.Trabajador;
import com.prueba.gestiontiendas.repositorio.AsignacionTrabajadorSeccionRepository;
import com.prueba.gestiontiendas.servicio.AsignacionTrabajadorSeccionService;
import com.prueba.gestiontiendas.servicio.SeccionService;
import com.prueba.gestiontiendas.servicio.TrabajadorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Husnain
 */
@Service
public class AsignacionServiceImpl implements AsignacionTrabajadorSeccionService {
    private final AsignacionTrabajadorSeccionRepository asignacionRepository;
    private final TrabajadorService trabajadorService;
    private final SeccionService seccionService;
    public AsignacionServiceImpl(AsignacionTrabajadorSeccionRepository asignacionRepository,
                                 TrabajadorService trabajadorService,
                                 SeccionService seccionService) {
        this.asignacionRepository = asignacionRepository;
        this.trabajadorService = trabajadorService;
        this.seccionService = seccionService;
    }

    public List<AsignacionTrabajadorSeccion> getAsignacionesTrabajador(Long trabajadorId) {
        return asignacionRepository.findByTrabajadorId(trabajadorId);
    }

    //FIXME: controlar si se excede de las horas necesarias de una sección???
    public AsignacionTrabajadorSeccion asignar(Long trabajadorId, Long seccionId, Integer horas) {
        Trabajador trabajador = trabajadorService.getTrabajador(trabajadorId);
        Seccion seccion = seccionService.getSeccion(seccionId);

        controlarSuperarHorasDisponibles(trabajador, seccionId, horas);

        AsignacionTrabajadorSeccion asignacion = asignacionRepository
                .findByTrabajadorIdAndSeccionId(trabajadorId, seccionId)
                .orElse(AsignacionTrabajadorSeccion.builder()
                        .trabajador(trabajador)
                        .seccion(seccion)
                        .horasAsignadas(0)
                        .build());

        asignacion.setHorasAsignadas(asignacion.getHorasAsignadas() + horas);

        return asignacionRepository.save(asignacion);
    }

    public void desasignar(Long trabajadorId, Long seccionId) {
        AsignacionTrabajadorSeccion asignacion = asignacionRepository
                .findByTrabajadorIdAndSeccionId(trabajadorId, seccionId)
                .orElseThrow(() ->
                        new EntityNotFoundException("exception.notfound.asignacion", trabajadorId, seccionId));

        asignacionRepository.delete(asignacion);
    }

    public List<AsignacionTrabajadorSeccion> getAsignacionesByTrabajadorId(Long trabajadorId) {
        trabajadorService.getTrabajador(trabajadorId); // Valida que existe
        return asignacionRepository.findByTrabajadorId(trabajadorId);
    }

    private void controlarSuperarHorasDisponibles(Trabajador trabajador, Long seccionId, Integer horas) {
        int horasYaAsignadas = calcularHorasAsignadas(trabajador);
        int horasDisponibles = trabajador.getHorasDisponibles() - horasYaAsignadas;

        if (horas > horasDisponibles) {
            throw new HorasInsuficientesException(horasDisponibles, horas);
        }
    }

    private int calcularHorasAsignadas(Trabajador trabajador) {
        return trabajador.getAsignaciones()
                .stream()
                .mapToInt(AsignacionTrabajadorSeccion::getHorasAsignadas)
                .sum();
    }


}
