package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListadoController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/mascotas")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "lista_mascotas";
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }
}