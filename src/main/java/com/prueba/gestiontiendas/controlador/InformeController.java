package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.InformeEstadoTiendaDto;
import com.prueba.gestiontiendas.dto.InformeHorasSinCubrirDto;
import com.prueba.gestiontiendas.servicio.InformeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Husnain
 */

@RestController
@RequestMapping("/api/tiendas/{codigoTienda}/informes")
public class InformeController {

    private final InformeService informeService;

    public InformeController(InformeService informeService) {
        this.informeService = informeService;
    }

    @GetMapping("/estado")
    public ResponseEntity<InformeEstadoTiendaDto> getInformeEstadoTienda(@PathVariable String codigoTienda) {
        return ResponseEntity.ok(informeService.getInformeEstadoTienda(codigoTienda));
    }

    @GetMapping("/horas-sin-cubrir")
    public ResponseEntity<InformeHorasSinCubrirDto> getInformeHorasSinCubrir(@PathVariable String codigoTienda) {
        return ResponseEntity.ok(informeService.getInformeHorasSinCubrir(codigoTienda));
    }
}
