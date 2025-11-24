package com.prueba.gestiontiendas.repositorio;

import com.prueba.gestiontiendas.modelo.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Husnain
 */
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    public List<Trabajador> findByTiendaCodigo(String codigo);
}
