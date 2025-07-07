package com.ceatformacion.mascotaspsi.controller;
import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.service.MascotaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerMascota {
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/mascotas")
    public String listarMascotas(Model model, HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (rol == null) {
            return "redirect:/login";
        }
        
        List<Mascota> mascotas;
        if ("ADMIN".equals(rol)) {
            mascotas = mascotaService.listarMascotas();
            model.addAttribute("esAdmin", true);
        } else {
            mascotas = mascotaService.listarMascotasPorUsuario(usuarioId);
            model.addAttribute("esAdmin", false);
        }
        
        model.addAttribute("mascotas", mascotas);
        return "lista_mascotas";
    }

    @GetMapping("/mascota/nueva")
    public String mostrarFormularioNuevo(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("mascota", new Mascota());
        return "formulario_mascota";
    }

    @PostMapping("/mascota/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        String rol = (String) session.getAttribute("rol");
        
        if (usuarioId == null) {
            return "redirect:/login";
        }

        // Si es USER, forzar que la mascota se asocie con el usuario actual
        if (!"ADMIN".equals(rol)) {
            mascotaService.guardarMascotaConUsuario(mascota, usuarioId);
        } else {
            mascotaService.guardarMascotaConUsuario(mascota, null);
        }
        
        return "redirect:/mascotas";
    }

    @GetMapping("/mascota/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            return "redirect:/mascotas";
        }
        
        Optional<Mascota> mascota = mascotaService.findById(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "formulario_mascota";
        }
        return "redirect:/mascotas";
    }

    @PostMapping("/mascota/eliminar/{id}")
    public String eliminarMascota(@PathVariable Integer id, HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            return "redirect:/mascotas";
        }
        
        mascotaService.deleteById(id);
        return "redirect:/mascotas";
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/buscar")
    public String buscarPorNombre (@RequestParam String nombre, Model model) {
        List<Mascota> resultadoBusqueda = mascotaRepository.findByNombreContainsIgnoreCase(nombre);
        model.addAttribute("mascotas", resultadoBusqueda);
        return "lista_mascotas";
    }
}