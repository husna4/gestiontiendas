package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.AsignacionRequestDto;
import com.prueba.gestiontiendas.dto.AsignacionResponseDto;
import com.prueba.gestiontiendas.mappers.AsignacionMapper;
import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import com.prueba.gestiontiendas.servicio.AsignacionTrabajadorSeccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Husnain
 */

@RestController
@RequestMapping("/api/trabajadores/{trabajadorId}/asignaciones")
public class AsignacionController {

    private final AsignacionTrabajadorSeccionService asignacionService;
    private final AsignacionMapper asignacionMapper;

    public AsignacionController(AsignacionTrabajadorSeccionService asignacionService,
                                AsignacionMapper asignacionMapper) {
        this.asignacionService = asignacionService;
        this.asignacionMapper = asignacionMapper;
    }

    @GetMapping
    public ResponseEntity<List<AsignacionResponseDto>> getAsignaciones(@PathVariable Long trabajadorId) {
        List<AsignacionResponseDto> asignaciones = asignacionService.getAsignacionesTrabajador(trabajadorId)
                .stream()
                .map(asignacionMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(asignaciones);
    }

    @PostMapping
    public ResponseEntity<AsignacionResponseDto> asignar(@PathVariable Long trabajadorId,
                                                         @Valid @RequestBody AsignacionRequestDto dto) {
        AsignacionTrabajadorSeccion asignacion = asignacionService.asignar(trabajadorId, dto.getSeccionId(),
                dto.getHorasAsignadas());
        return ResponseEntity.status(HttpStatus.CREATED).body(asignacionMapper.toResponseDto(asignacion));
    }

    @DeleteMapping("/{seccionId}")
    public ResponseEntity<Void> desasignar(@PathVariable Long trabajadorId, @PathVariable Long seccionId) {
        asignacionService.desasignar(trabajadorId, seccionId);
        return ResponseEntity.noContent().build();
    }
}
