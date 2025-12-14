package com.prueba.gestiontiendas.repositorio;

import com.prueba.gestiontiendas.modelo.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Husnain
 */
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    Optional<Tienda> findByCodigo(String codigo);
}
