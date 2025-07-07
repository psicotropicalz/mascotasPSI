package com.ceatformacion.mascotaspsi.service;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    // Nuevos métodos necesarios
    public List<Mascota> listarMascotasPorUsuario(Long usuarioId) {
        return mascotaRepository.findByUsuarioId(usuarioId);
    }
    
    public Optional<Mascota> findById(Integer id) {
        return mascotaRepository.findById(id);
    }
    
    public void deleteById(Integer id) {
        mascotaRepository.deleteById(id);
    }
    
    public Mascota guardarMascotaConUsuario(Mascota mascota, Long usuarioId) {
        if (usuarioId != null) {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            mascota.setUsuario(usuario);
        }
        return mascotaRepository.save(mascota);
    }
}