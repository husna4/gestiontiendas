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
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(name = "horas_disponibles", nullable = false)
    private int horasDisponibles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    @OneToMany(mappedBy = "trabajador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsignacionTrabajadorSeccion> asignaciones = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "trabajador_aptitud",
            joinColumns = @JoinColumn(name = "trabajador_id"),
            inverseJoinColumns = @JoinColumn(name = "aptitud_id")
    )
    private List<Aptitud> aptitudes = new ArrayList<>();
}
