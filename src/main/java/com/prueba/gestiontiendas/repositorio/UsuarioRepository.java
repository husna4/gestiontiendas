package com.prueba.gestiontiendas.repositorio;

import com.prueba.gestiontiendas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Husnain
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsernameAndPassword(String username, String password);
}
