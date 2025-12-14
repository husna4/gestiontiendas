package com.prueba.gestiontiendas;

import com.prueba.gestiontiendas.dto.TrabajadorRequestDto;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.prueba.gestiontiendas.constantes.ConstantesGlobales.MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Husnain
 */

public class ValidacionCamposDtoTest {

    private final Validator validator;

    public ValidacionCamposDtoTest() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    @Test
    void pruebaValidacionTrabajador_validarHorasMaximas_correcto() {
        TrabajadorRequestDto trabajador = crearTrabajador(MAXIMO_HORAS_DISPONIBLES_TRABAJADOR);

        Set<ConstraintViolation<TrabajadorRequestDto>> violations = validator.validate(trabajador);
        assertThat(violations).isEmpty();
    }

    @Test
    void pruebaValidacionTrabajador_validarHorasMaximas_error() {
        TrabajadorRequestDto trabajador = crearTrabajador(MAXIMO_HORAS_DISPONIBLES_TRABAJADOR + 1);
        String mensajeEsperado = "Las horas disponibles no pueden ser mayores de " + MAXIMO_HORAS_DISPONIBLES_TRABAJADOR;

        Set<ConstraintViolation<TrabajadorRequestDto>> violations = validator.validate(trabajador);
        assertThat(violations).hasSize(1);

        assertThat(violations)
                .extracting(ConstraintViolation::getPropertyPath)
                .extracting(Path::toString)
                .contains("horasDisponibles");

    }

    private TrabajadorRequestDto crearTrabajador(int horasDisponibles) {
        TrabajadorRequestDto trabajador = new TrabajadorRequestDto();
        trabajador.setDni("12345678A");
        trabajador.setNombre("Juan");
        trabajador.setApellidos("Perez");
        trabajador.setHorasDisponibles(horasDisponibles);
        return trabajador;
    }
}
