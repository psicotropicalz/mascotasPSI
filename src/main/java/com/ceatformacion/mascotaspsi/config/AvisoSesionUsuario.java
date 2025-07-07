package com.ceatformacion.mascotaspsi.config;

import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class AvisoSesionUsuario {

    private final UsuarioRepository usuarioRepository;

    public AvisoSesionUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @ModelAttribute
    public void addUserInfoToSession(Authentication authentication, HttpSession session) {
        if (authentication != null && session.getAttribute("usuarioId") == null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

            if (usuario != null) {
                session.setAttribute("usuarioId", usuario.getId());
                session.setAttribute("rol", usuario.getRol());
                session.setAttribute("username", usuario.getUsername());
            }
        }
    }
}
