package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
