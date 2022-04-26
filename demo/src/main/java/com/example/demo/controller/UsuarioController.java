package com.example.demo.controller;

import java.util.List;

import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.model.Usuario;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("")
	public Page<Usuario> all(){
		return service.findAll( PageRequest.of(0, 10) );
	}

	@GetMapping("page/{pageNumber}")
	public Page<Usuario> getByPage(@PathVariable Integer pageNumber) {
		return service.findAll(
				PageRequest.of(pageNumber, 10)
		);
	}

	@GetMapping("{id}")
	public Usuario get(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PostMapping("")
	public Usuario create(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@PutMapping("{id}")
	public Usuario update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Usuario usuarioDB = service.findById(id);

		if (usuarioDB != null) {
			usuarioDB.setName(usuario.getName());
			usuarioDB.setEmail(usuario.getEmail());
			usuarioDB.setPassword(usuario.getPassword());
			usuarioDB.setEnabled(usuario.isEnabled());
			usuarioDB.setCreatedAt(usuario.getCreatedAt());

			return service.save(usuarioDB);
		}

		return null;

	}
	
	@DeleteMapping("{id}")
	public void borrarUsuario(@PathVariable Integer id) {
		service.deleteById(id);
	}

}
