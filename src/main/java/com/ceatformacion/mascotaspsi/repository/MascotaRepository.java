package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByUsuarioId(Long usuarioId);
    List<Mascota> findByNombreContainsIgnoreCase(String nombre);
}