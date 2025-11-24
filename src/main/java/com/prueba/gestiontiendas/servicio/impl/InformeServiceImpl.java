package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.cliente.TiendaExternaClient;
import com.prueba.gestiontiendas.dto.*;
import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import com.prueba.gestiontiendas.modelo.Seccion;
import com.prueba.gestiontiendas.modelo.Tienda;
import com.prueba.gestiontiendas.servicio.InformeService;
import com.prueba.gestiontiendas.servicio.SeccionService;
import com.prueba.gestiontiendas.servicio.TiendaService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Husnain
 */

@Service
public class InformeServiceImpl implements InformeService {
    private final TiendaService tiendaService;
    private final SeccionService seccionService;
    private final TiendaExternaClient tiendaExternaClient;

    public InformeServiceImpl(TiendaService tiendaService, SeccionService seccionService,
                              TiendaExternaClient tiendaExternaClient) {
        this.tiendaExternaClient = tiendaExternaClient;
        this.tiendaService = tiendaService;
        this.seccionService = seccionService;
    }

    public InformeEstadoTiendaDto getInformeEstadoTienda(String codigoTienda) {
        Tienda tienda = tiendaService.getTiendaByCodigo(codigoTienda);
        Optional<TiendaExternaDto> tiendaExternaDto = tiendaExternaClient.getTienda(codigoTienda);

        return InformeEstadoTiendaDto.builder()
                .nombreTienda(tienda.getNombre())
                .seccionesEstadoDto(buildSeccionesEstado(tienda.getId()))
                .ciudad(tiendaExternaDto.map(TiendaExternaDto::getCity).orElse(null))
                .direccion(tiendaExternaDto.map(TiendaExternaDto::getAddress).orElse(null))
                .build();
    }

    private List<SeccionEstadoDto> buildSeccionesEstado(Long idTienda) {
        return seccionService.getSecciones().stream()
                .map(s -> SeccionEstadoDto.builder()
                        .nombre(s.getNombre())
                        .horasTrabajoNecesarias(s.getHorasTrabajoNecesarias())
                        .trabajadores(buildTrabajadorSeccionDto(idTienda, s))
                        .build())
                .toList();
    }

    private List<TrabajadorSeccionDto> buildTrabajadorSeccionDto(Long idTienda, Seccion seccion) {
        return seccion.getAsignaciones()
                .stream()
                .filter(asignacion -> asignacion.getTrabajador().getTienda().getId().equals(idTienda))
                .map(asignacion -> TrabajadorSeccionDto.builder()
                        .nombre(asignacion.getTrabajador().getNombre())
                        .apellidos(asignacion.getTrabajador().getApellidos())
                        .dni(asignacion.getTrabajador().getDni())
                        .horasAsignadas(asignacion.getHorasAsignadas())
                        .build())
                .toList();
    }

    public InformeHorasSinCubrirDto getInformeHorasSinCubrir(String codigoTienda) {
        Tienda tienda = tiendaService.getTiendaByCodigo(codigoTienda);
        Optional<TiendaExternaDto> tiendaExternaDto = tiendaExternaClient.getTienda(codigoTienda);

        return InformeHorasSinCubrirDto.builder()
                .nombreTienda(tienda.getNombre())
                .seccionesSinCubrir(buildSeccionesSinCubrir(tienda.getId()))
                .ciudad(tiendaExternaDto.map(TiendaExternaDto::getCity).orElse(null))
                .direccion(tiendaExternaDto.map(TiendaExternaDto::getAddress).orElse(null))
                .build();
    }

    private List<SeccionSinCubrirDto> buildSeccionesSinCubrir(Long idTienda) {
        Map<Seccion, Integer> mapSeccionesSinCubrir = buildMapSeccionesSinCubrir(idTienda);
        List<SeccionSinCubrirDto> seccionesSinCubrirDto = new ArrayList<>();

        mapSeccionesSinCubrir.forEach((seccion, horasNoCubiertas) -> seccionesSinCubrirDto.add(
                SeccionSinCubrirDto.builder()
                        .nombre(seccion.getNombre())
                        .horasNecesarias(seccion.getHorasTrabajoNecesarias())
                        .horasCubiertas(seccion.getHorasTrabajoNecesarias() - horasNoCubiertas)
                        .horasSinCubrir(horasNoCubiertas)
                        .build()));

        return seccionesSinCubrirDto;
    }

    /**
     * Devuelve las secciones sin cubrir de una tienda con sus horas sin cubrir
     *
     * @param idTienda
     * @return
     */
    private Map<Seccion, Integer> buildMapSeccionesSinCubrir(Long idTienda) {
        Map<Seccion, Integer> seccionesSinCubrir = new HashMap<>();

        seccionService.getSecciones().forEach(seccion -> {
            int horasNecesarias = seccion.getHorasTrabajoNecesarias();
            int horasCubiertas = seccion.getAsignaciones().stream()
                    .filter(asignacion -> asignacion.getTrabajador().getTienda().getId().equals(idTienda))
                    .mapToInt(AsignacionTrabajadorSeccion::getHorasAsignadas)
                    .sum();

            int horasSinCubrir = horasNecesarias - horasCubiertas;
            if (horasSinCubrir > 0) {
                seccionesSinCubrir.put(seccion, horasSinCubrir);
            }
        });

        return seccionesSinCubrir;
    }
}
