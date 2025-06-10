package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mascota")
public class ControllerMascota {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "formulario_mascota";  // Cambiado a nueva_mascota.html
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {
        mascotaRepository.save(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de mascota inválido: " + id));
        model.addAttribute("mascota", mascota);
        return "formulario_mascota";  // Cambiado a nueva_mascota.html
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Integer id) {
        mascotaRepository.deleteById(id);
        return "redirect:/mascotas";
    }
}