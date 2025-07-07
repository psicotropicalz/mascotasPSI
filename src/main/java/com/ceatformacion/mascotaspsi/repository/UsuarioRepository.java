package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //necesitamos que el repositorio busque por username
    Optional<Usuario> findByUsername(String username);
}