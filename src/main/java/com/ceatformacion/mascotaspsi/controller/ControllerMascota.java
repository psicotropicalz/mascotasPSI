package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.service.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerMascota {

    private final MascotaService mascotaService;

    public ControllerMascota(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/nueva")
    public String formularioMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "nueva_mascota";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {
        mascotaService.guardarMascota(mascota);
        return "registro_exitoso"; // Ya no redirige al index, sino a una vista
    }

    @GetMapping("/lista")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.listarMascotas());
        return "lista_mascotas";
    }

}
