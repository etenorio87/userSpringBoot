package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.payloads.request.LoginRequestDTO;
import com.example.demo.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }


    @PostMapping("login")
    public Usuario login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return service.authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

    @GetMapping("getUser")
    public Usuario getUser() {
        return service.getAuthenticatedUser();
    }


}
