package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistorialController {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    private HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/mascota/{mascotaId}/historial")
    public String getHistorialById(@PathVariable int mascotaId) {
        historialRepository.findById(mascotaId).get();
        return "historial_mascota";
    }


    // API REST -> recibe y envía los datos en formato JSON
    @ResponseBody // API REST para guardar una nueva entrada en el historial
    @PostMapping("api/historial")
    public Historial saveHistorial(@RequestBody Historial historial) {
        return historialRepository.save(historial);
    }

    @ResponseBody
    @GetMapping("/mascota/{mascotaId}")
    public List<Historial> findByMascotaId(@PathVariable int mascotaId) {
        return historialService.getHistorialByMascota(mascotaId);
    }

    @GetMapping("/consulta/{mascotaId}")
    public String getHistorialByMascotaId(@PathVariable int mascotaId, Model model) {
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow();
        List<Historial> historialList = historialRepository.findByMascotaId(mascotaId);
        model.addAttribute("mascota", mascota);
        model.addAttribute("historial", historialList);
        model.addAttribute("nuevaVisita", new Historial());
        return "historial_mascota";
    }

    //Guardar la vista
    @PostMapping("/consulta/{mascotaId}")
    public String registrarVisita(@PathVariable int mascotaId, @ModelAttribute("nuevaVisita") Historial nuevaVisita) {
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow();
        nuevaVisita.setMascota(mascota);
        historialRepository.save(nuevaVisita);
        return "redirect:/consulta/" + mascotaId;
    }



}
