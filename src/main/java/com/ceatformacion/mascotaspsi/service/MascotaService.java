package com.ceatformacion.mascotaspsi.service;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

}
