package com.prueba.gestiontiendas.repositorio;

import com.prueba.gestiontiendas.modelo.AsignacionTrabajadorSeccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface AsignacionTrabajadorSeccionRepository extends JpaRepository<AsignacionTrabajadorSeccion, Long> {

    Optional<AsignacionTrabajadorSeccion> findByTrabajadorIdAndSeccionId(Long trabajadorId, Long seccionId);

    List<AsignacionTrabajadorSeccion> findByTrabajadorId(Long trabajadorId);
}
