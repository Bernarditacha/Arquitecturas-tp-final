package com.practico.integrador.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;
import com.practico.integrador.utils.ResponseError;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
	//@ApiResponse(code = 401, message = "not authorized!"),
	@ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/login")
	public String findByUsuarioAndContrasenia(@RequestParam("user") String username, @RequestParam("password") String pwd) {		
		Usuario getUser = repository.findByUsuarioAndContrasenia(username, pwd);
		System.out.println(getUser);
		if (getUser != null) {
			return getJWTToken(getUser, true);
		} else {
			return "No existe el usuario";
		}
		
	}
	
	//Genero el token.
	private String getJWTToken(Usuario user, boolean link) {
		String secretKey = "mySecretKey";
		String roles = "ROLE_USER";
		if (link) {
			roles += ",LINK";
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(roles);
		
		String token = Jwts
				.builder()
				.setId(user.getId().toString())
				.setSubject(user.getUsuario())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	@ApiOperation(value = "Alta de usuario", response = Usuario.class)
	@PostMapping("/nuevo")
	public Usuario addUsuario(@RequestBody Usuario u) {
		return repository.save(u);
	}

	@ApiOperation(value = "Edicion de usuario", response = Usuario.class)
	@PutMapping("/editar/{id}")
	Object updatePersona(@RequestBody Usuario newUsuario, @PathVariable Long id) {
		ResponseError error = new ResponseError("Error", 400, "No existe el usuario");
		Optional<Usuario> usuario = repository.findById(id);
		if(usuario.isPresent()) {
			usuario.get().setUsuario(newUsuario.getUsuario());
			usuario.get().setContrasenia(newUsuario.getContrasenia());
			repository.save(usuario.get());
			return usuario;
		} else {
			return error;
		}
	}

	// Baja
	@ApiOperation(value = "Baja de usuario", response = Usuario.class)
	@DeleteMapping("/{id}")
	void deleteUsuario(@PathVariable Long id) {
		repository.deleteById(id);
	}

}