package com.prueba.gestiontiendas.excepciones;

import lombok.Getter;

/**
 * @author Husnain
 */

@Getter
public class PasswordIncorrectoException extends RuntimeException {
    private String idMensaje;

    public PasswordIncorrectoException(String idMensaje) {
        super("La contraseña es incorrecta");
        this.idMensaje = idMensaje;
    }
}
