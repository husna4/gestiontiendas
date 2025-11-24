package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.SeccionAptitudesDto;
import com.prueba.gestiontiendas.dto.TiendaSeccionesAptitudesDto;
import com.prueba.gestiontiendas.servicio.SeccionService;
import com.prueba.gestiontiendas.servicio.TiendaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Husnain
 */

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;

    private final SeccionService seccionService;
    private final ModelMapper modelMapper;

    public TiendaController(TiendaService tiendaService, SeccionService seccionService, ModelMapper modelMapper) {
        this.tiendaService = tiendaService;
        this.seccionService = seccionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{codigoTienda}/secciones-aptitudes")
    public ResponseEntity<TiendaSeccionesAptitudesDto> getTiendaSetup(@PathVariable String codigoTienda) {
        TiendaSeccionesAptitudesDto dto = modelMapper.map(tiendaService.getTiendaByCodigo(codigoTienda),
                TiendaSeccionesAptitudesDto.class);

        List<SeccionAptitudesDto> secciones = seccionService.getSecciones()
                .stream()
                .map(seccion -> modelMapper.map(seccion, SeccionAptitudesDto.class)).toList();

        dto.setSecciones(secciones);

        return ResponseEntity.ok(dto);
    }
}
