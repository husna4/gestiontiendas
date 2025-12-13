package com.prueba.gestiontiendas.excepciones;

import lombok.Getter;

/**
 * @author Husnain
 */

@Getter
public class NombreUsuarioOPasswordIncorrectoException extends RuntimeException {
    private String idMensaje;

    public NombreUsuarioOPasswordIncorrectoException(String idMensaje) {
        super("La contraseña es incorrecta");
        this.idMensaje = idMensaje;
    }
}
