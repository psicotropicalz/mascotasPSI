package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
    List<Historial> findByMascotaId(int mascotaId);
    List<Historial> findByMascota(Mascota mascota);
}