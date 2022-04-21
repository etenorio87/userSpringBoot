package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/obtener")
	public List<Usuario> allUsuarios(){
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/insertar")
	public Usuario insertarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("/actualizar/{dni}")
	public Usuario actualizarUsuario(@PathVariable String dni, @RequestBody Usuario usuario) {
		Usuario usuarioActualizado = usuarioRepository.findByDni(dni);
		usuarioActualizado.setNombre(usuario.getNombre());
		usuarioActualizado.setClave(usuario.getClave());
		usuarioActualizado.setDni(usuario.getDni());
		
		return usuarioRepository.save(usuarioActualizado);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrarUsuario(@PathVariable int id) {
		usuarioRepository.deleteById(id);
	}
	
	@DeleteMapping("/borrarDni/{dni}")
	public void borrarUsuarioDni(@PathVariable String dni) {
		Usuario usuarioBorrar = usuarioRepository.findByDni(dni);
		usuarioRepository.deleteById(usuarioBorrar.getId());
	}
	
	
}
