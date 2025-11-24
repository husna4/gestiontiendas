package com.prueba.gestiontiendas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Husnain
 */

@Entity
@Getter
@Setter
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "horas_trabajo_necesarias", nullable = false)
    private Integer horasTrabajoNecesarias;

    @OneToMany(mappedBy = "seccion")
    private List<AsignacionTrabajadorSeccion> asignaciones = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "seccion_aptitud",
            joinColumns = @JoinColumn(name = "seccion_id"),
            inverseJoinColumns = @JoinColumn(name = "aptitud_id")
    )
    private List<Aptitud> aptitudes = new ArrayList<>();

}
