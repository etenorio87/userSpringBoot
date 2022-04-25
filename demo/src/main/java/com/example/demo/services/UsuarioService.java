package com.example.demo.services;

import com.example.demo.domain.model.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.services.base.BaseService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Integer, IUsuarioRepository> {
    private final AuthenticationManager authenticationManager;


    public UsuarioService(AuthenticationManager authenticationManager, IUsuarioRepository repository) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    public Usuario getByUsername(String username) {
        return repository.findByEmail(username);
    }

    public Usuario authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            return (Usuario) authentication.getPrincipal();
        }

        return null;
    }


    public Usuario getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return   (Usuario) auth.getPrincipal() ;
    }
}
