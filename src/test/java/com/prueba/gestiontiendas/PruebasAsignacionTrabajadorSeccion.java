package com.prueba.gestiontiendas;

import com.prueba.gestiontiendas.excepciones.HorasInsuficientesException;
import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import com.prueba.gestiontiendas.modelo.Seccion;
import com.prueba.gestiontiendas.modelo.Trabajador;
import com.prueba.gestiontiendas.repositorio.AsignacionTrabajadorSeccionRepository;
import com.prueba.gestiontiendas.servicio.SeccionService;
import com.prueba.gestiontiendas.servicio.TrabajadorService;
import com.prueba.gestiontiendas.servicio.impl.AsignacionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Husnain
 */

@ExtendWith(MockitoExtension.class)
public class PruebasAsignacionTrabajadorSeccion {

    @Mock
    private AsignacionTrabajadorSeccionRepository asignacionRepository;

    @Mock
    private TrabajadorService trabajadorService;

    @Mock
    private SeccionService seccionService;

    @InjectMocks
    private AsignacionServiceImpl asignacionService;

    @Test
    void pruebaAsignar_trabajadorSinAsignacionPrevia_correcto() {
        long trabajadorId = 1L;
        long seccionId = 1L;
        int horas = MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
        int horasAAsignar = 8;

        Trabajador trabajador = crearTrabajador(trabajadorId, horas);
        Seccion seccion = crearSeccion(seccionId);
        AsignacionTrabajadorSeccion asignacionTrabajadorSeccionEsperado = crearAsignacion(trabajador, seccion, horas);

        when(trabajadorService.getTrabajador(trabajadorId)).thenReturn(crearTrabajador(trabajadorId, horas));
        when(seccionService.getSeccion(seccionId)).thenReturn(crearSeccion(seccionId));
        when(asignacionRepository.findByTrabajadorIdAndSeccionId(trabajadorId, seccionId))
                .thenReturn(Optional.empty());
        when(asignacionRepository.save(any(AsignacionTrabajadorSeccion.class)))
                .thenReturn(asignacionTrabajadorSeccionEsperado);

        AsignacionTrabajadorSeccion asignacionTrabajadorSeccion = asignacionService.asignar(trabajadorId,
                seccionId, horasAAsignar);
        assertNotNull(asignacionTrabajadorSeccion);

        verify(trabajadorService).getTrabajador(trabajadorId);
        verify(seccionService).getSeccion(seccionId);
        verify(asignacionRepository).findByTrabajadorIdAndSeccionId(trabajadorId, seccionId);
        verify(asignacionRepository).save(any(AsignacionTrabajadorSeccion.class));

    }

    @Test
    void pruebaAsginar_erorAlSuperarHorasDisponibles() {
        long trabajadorId = 1L;
        long seccionId = 1L;
        int horas = MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
        int horasAAsignar = 3;
        int horasYaAsignadas = 6;

        String mensajeErrorEsperado = String.format("El trabajador no tiene suficientes horas disponibles. Disponibles %d, Solicitadas %d",
                horas - horasYaAsignadas, horasAAsignar);

        Trabajador trabajador = crearTrabajador(trabajadorId, horas);
        Seccion seccion = crearSeccion(seccionId);
        AsignacionTrabajadorSeccion asignacionExistente = crearAsignacion(trabajador, seccion, horasYaAsignadas);

        when(trabajadorService.getTrabajador(trabajadorId)).thenReturn(trabajador);
        when(seccionService.getSeccion(seccionId)).thenReturn(seccion);

        HorasInsuficientesException exception = assertThrows(HorasInsuficientesException.class,
                () -> asignacionService.asignar(trabajadorId, seccionId, horasAAsignar));

        assertEquals(mensajeErrorEsperado, exception.getMessage());

        verify(trabajadorService).getTrabajador(trabajadorId);
        verify(seccionService).getSeccion(seccionId);
        verify(asignacionRepository, never()).save(any(AsignacionTrabajadorSeccion.class));
    }

    private Trabajador crearTrabajador(long id, int numHorasDisponibles) {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(id);
        trabajador.setDni("12345678A");
        trabajador.setNombre("Juan");
        trabajador.setApellidos("Perez");
        trabajador.setHorasDisponibles(numHorasDisponibles);

        return trabajador;
    }

    private Seccion crearSeccion(long id) {
        Seccion seccion = new Seccion();
        seccion.setId(id);
        seccion.setNombre("Seccion 1");
        seccion.setHorasTrabajoNecesarias(16);
        return seccion;
    }

    private AsignacionTrabajadorSeccion crearAsignacion(Trabajador trabajador, Seccion seccion, int horas) {
        AsignacionTrabajadorSeccion asignacion = new AsignacionTrabajadorSeccion();
        asignacion.setTrabajador(trabajador);
        asignacion.setSeccion(seccion);
        asignacion.setHorasAsignadas(horas);
        trabajador.getAsignaciones().add(asignacion);
        seccion.getAsignaciones().add(asignacion);

        return asignacion;
    }

}
