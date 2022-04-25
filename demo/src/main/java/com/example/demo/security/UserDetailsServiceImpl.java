package com.example.demo.security;

import com.example.demo.domain.model.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final IUsuarioRepository repository;

    public UserDetailsServiceImpl(IUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Searching for user: {} ...", username);
        Usuario usuario = repository.findByEmail(username);

        if (usuario == null) {
            String errorMsg = String.format("User with login name '%s' not found.", username);
            log.error(errorMsg);
            throw new UsernameNotFoundException(errorMsg);
        }

        log.info("username: {}, active = {}, with roles: {}", usuario.getUsername(), usuario.isEnabled(), usuario.getAuthorities());

        return usuario;
    }

}
