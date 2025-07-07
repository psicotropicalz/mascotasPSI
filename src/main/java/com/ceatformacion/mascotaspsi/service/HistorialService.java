package com.ceatformacion.mascotaspsi.service;

import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {
    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }


    // Creamos un metodo que obtiene el historial por mascota
    public List<Historial> getHistorialByMascota(int mascotaId) {
        return historialRepository.findByMascotaId(mascotaId);
    }

    // Guardar la Entrada en el Historial
    public Historial saveHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    // Eliminar entrada del Historial
    public void deleteHistorial(int historialId) {
        historialRepository.deleteById(historialId);
    }

    public Historial updateHistorialByMascotaId(int mascotaId, Historial historial) {
        if (historialRepository.existsById(mascotaId)) {
            return historialRepository.save(historial);
        } else {
            throw new RuntimeException("Historial no encontrado para el id de mascota: " + mascotaId);
        }
    }


}
