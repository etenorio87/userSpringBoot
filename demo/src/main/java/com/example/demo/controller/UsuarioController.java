package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("")
	public List<Usuario> all(){
		return usuarioRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<Usuario> get(@PathVariable Integer id){
		return usuarioRepository.findById(id);
	}
	
	@PostMapping("")
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("{id}")
	public Usuario update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioDB = usuarioRepository.findById(id);

		if (usuarioDB.isPresent()) {
			Usuario usuarioActualizado = usuarioDB.get();
			usuarioActualizado.setName(usuario.getName());
			usuarioActualizado.setEmail(usuario.getEmail());
			usuarioActualizado.setPassword(usuario.getPassword());
			usuarioActualizado.setEnabled(usuario.isEnabled());
			usuarioActualizado.setCreatedAt(usuario.getCreatedAt());

			return usuarioRepository.save(usuarioActualizado);
		}


		return null;

	}
	
	@DeleteMapping("{id}")
	public void borrarUsuario(@PathVariable Integer id) {
		usuarioRepository.deleteById(id);
	}
	

	
	
}
