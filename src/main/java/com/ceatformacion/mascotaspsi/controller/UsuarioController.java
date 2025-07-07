package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "altaUsuario";
    }
    
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            return "redirect:/login?registroExitoso";
        } catch (RuntimeException e) {
            return "redirect:/registro?error";
        }
    }
}