package com.prueba.gestiontiendas.modelo;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Husnain
 */

@Entity
@Table(name = "asignacion_trabajador_seccion")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AsignacionTrabajadorSeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seccion_id", nullable = false)
    private Seccion seccion;

    @Column(name = "horas_asignadas", nullable = false)
    private Integer horasAsignadas;
}
