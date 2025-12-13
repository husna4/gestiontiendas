package com.prueba.gestiontiendas.excepciones;

import lombok.Getter;

/**
 * @author Husnain
 */

@Getter
public class EntityNotFoundException extends RuntimeException{

    private String nombreEntidad;
    private String nombreCampo;
    private String valorCampo;

    private String idMensaje;
    private Object[] params;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String nombreEntidad, String nombreCampo, String valor) {
        super(String.format("%s con %s: %s no encontrado", nombreEntidad, nombreCampo, valor));
        this.nombreEntidad = nombreEntidad;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valor;
    }

    public EntityNotFoundException(String idMensaje, Object... params) {
        super("Entidad no encontrada");
        this.idMensaje = idMensaje;
        this.params = params;
    }
}
