package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.TrabajadorRequestDto;
import com.prueba.gestiontiendas.dto.TrabajadorResponseDto;
import com.prueba.gestiontiendas.modelo.Trabajador;
import com.prueba.gestiontiendas.servicio.TrabajadorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Husnain
 */

@RestController
@RequestMapping("/api")
public class TrabajadorController {
    private final TrabajadorService trabajadorService;
    private final ModelMapper modelMapper;

    public TrabajadorController(TrabajadorService trabajadorService, ModelMapper modelMapper) {
        this.trabajadorService = trabajadorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/tiendas/{codigoTienda}/trabajadores")
    public ResponseEntity<List<TrabajadorResponseDto>> getTrabajadoresByTienda(
            @PathVariable String codigoTienda) {

        List<Trabajador> trabajadores = trabajadorService.getTrabajadoresByCodigoTienda(codigoTienda);
        List<TrabajadorResponseDto> response = trabajadores.stream()
                .map(trabajador -> modelMapper.map(trabajador, TrabajadorResponseDto.class))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/tiendas/{codigoTienda}/trabajadores")
    public ResponseEntity<TrabajadorResponseDto> crear(@PathVariable String codigoTienda,
            @Valid @RequestBody TrabajadorRequestDto dto) {

        Trabajador trabajador = trabajadorService.crear(codigoTienda, modelMapper.map(dto, Trabajador.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(trabajador, TrabajadorResponseDto.class));
    }

    @PutMapping("/trabajadores/{id}")
    public ResponseEntity<TrabajadorResponseDto> editar(@PathVariable Long id,
            @Valid @RequestBody TrabajadorRequestDto dto) {
        Trabajador trabajador = trabajadorService.editar(id, dto);
        return ResponseEntity.ok(modelMapper.map(trabajador, TrabajadorResponseDto.class));
    }

    @DeleteMapping("/trabajadores/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        trabajadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
