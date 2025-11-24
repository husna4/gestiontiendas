package com.prueba.gestiontiendas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Husnain
 */

@Entity
@Getter
@Setter
public class Aptitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
}
