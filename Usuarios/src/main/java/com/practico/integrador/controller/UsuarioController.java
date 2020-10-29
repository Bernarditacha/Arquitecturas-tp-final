package com.practico.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Qualifier("usuarioRepository")
	@Autowired
	private final UsuarioRepository repository;

	public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}

	// Recupero todos los usuarios
	@GetMapping
	public Iterable<Usuario> getUsuarios() {
		return repository.findAll();
	}

	// Permite recuperar a determinado usuario segun pass y contrasenia
	@GetMapping("/findByUsuarioAndContrasenia/{usuario}/{contrasenia}")
	public Usuario findByUsuarioAndContrasenia(@PathVariable("usuario") String usuario, @PathVariable("contrasenia") String contrasenia) {
		return repository.findByUsuarioAndContrasenia(usuario, contrasenia);
	}

	// Alta
	@PostMapping("/nuevo")
	public Usuario addUsuario(@RequestBody Usuario u) {
		return repository.save(u);
	}

	// Modificacion
	@PutMapping("/editar/{id}")
	Usuario updatePersona(@RequestBody Usuario newUsuario, @PathVariable Long id) {
		Usuario usuario = repository.findById(id).get();
		usuario.setUsuario(newUsuario.getUsuario());
		usuario.setContrasenia(newUsuario.getContrasenia());
		repository.save(usuario);
		return usuario;
	}

	// Baja
	@DeleteMapping("/{id}")
	void deleteUsuario(@PathVariable Long id) {
		repository.deleteById(id);
	}

}