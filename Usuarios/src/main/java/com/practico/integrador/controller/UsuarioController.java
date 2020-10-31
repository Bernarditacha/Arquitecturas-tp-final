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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("usuarios")
@Api(value = "UsuarioControllerJpa", description = "REST API Usuario")
public class UsuarioController {

	@Qualifier("usuarioRepository")
	@Autowired
	private final UsuarioRepository repository;

	public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}

	@ApiOperation(value = "Recupero el universo de usuarios", response = Usuario.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
	@ApiResponse(code = 401, message = "not authorized!"),
	@ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping
	public Iterable<Usuario> getUsuarios() {
		return repository.findAll();
	}

	@ApiOperation(value = "Recupero un usuario con determinado nombre y contraseña", response = Usuario.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
	@ApiResponse(code = 401, message = "not authorized!"),
	@ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/findByUsuarioAndContrasenia/{usuario}/{contrasenia}")
	public Usuario findByUsuarioAndContrasenia(@PathVariable("usuario") String usuario,
			@PathVariable("contrasenia") String contrasenia) {
		return repository.findByUsuarioAndContrasenia(usuario, contrasenia);
	}

	@ApiOperation(value = "Alta de usuario", response = Usuario.class)
	@PostMapping("/nuevo")
	public Usuario addUsuario(@RequestBody Usuario u) {
		return repository.save(u);
	}

	@ApiOperation(value = "Edicion de usuario", response = Usuario.class)
	@PutMapping("/editar/{id}")
	Usuario updatePersona(@RequestBody Usuario newUsuario, @PathVariable Long id) {
		Usuario usuario = repository.findById(id).get();
		usuario.setUsuario(newUsuario.getUsuario());
		usuario.setContrasenia(newUsuario.getContrasenia());
		repository.save(usuario);
		return usuario;
	}

	// Baja
	@ApiOperation(value = "Baja de usuario", response = Usuario.class)
	@DeleteMapping("/{id}")
	void deleteUsuario(@PathVariable Long id) {
		repository.deleteById(id);
	}

}