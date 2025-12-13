package com.prueba.gestiontiendas.excepciones;

import lombok.Getter;

/**
 * @author Husnain
 */

@Getter
public class DuplicadoException extends RuntimeException {

    private final Object[] datosClave;

    public DuplicadoException(String message, Object... datosClave) {
        super(message);
        this.datosClave = datosClave;
    }
}
