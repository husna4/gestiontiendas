package com.prueba.gestiontiendas.excepciones;

import lombok.Getter;

/**
 * @author Husnain
 */

@Getter
public class HorasInsuficientesException extends RuntimeException{

    private final Integer horasDisponibles;
    private final Integer horasSolicitadas;

    public HorasInsuficientesException(String message) {
        super(message);
        this.horasDisponibles = null;
        this.horasSolicitadas = null;
    }

    public HorasInsuficientesException(int horasDisponibles, int horasSolicitadas) {
        super(String.format("El trabajador no tiene suficientes horas disponibles. Disponibles %d, Solicitadas %d",
                        horasDisponibles, horasSolicitadas));
        this.horasDisponibles = horasDisponibles;
        this.horasSolicitadas = horasSolicitadas;
    }
}
