package com.prueba.gestiontiendas.mappers;

import com.prueba.gestiontiendas.dto.AsignacionResponseDto;
import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import org.springframework.stereotype.Component;

/**
 * @author Husnain
 */

@Component
public class AsignacionMapper {

    public AsignacionResponseDto toResponseDto(AsignacionTrabajadorSeccion asignacion) {
        return AsignacionResponseDto.builder()
                .id(asignacion.getId())
                .trabajadorId(asignacion.getTrabajador().getId())
                .nombreTrabajador(asignacion.getTrabajador().getNombre())
                .seccionId(asignacion.getSeccion().getId())
                .nombreSeccion(asignacion.getSeccion().getNombre())
                .horasAsignadas(asignacion.getHorasAsignadas())
                .build();
    }
}
