package com.practico.integrador.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.practico.integrador.dto.UsuarioDTO;
import com.practico.integrador.dto.ViajeDTO;
import com.practico.integrador.model.Viaje;
import com.practico.integrador.pojo.UsuarioPOJO;
import com.practico.integrador.repository.ViajeRepository;
import com.practico.integrador.utils.ReporteUsuariosViajes;
import com.practico.integrador.utils.ReporteZonasVisitadas;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("viajes")
@Api(value = "ViajeControllerJpa", description = "REST API Viajes")
public class ViajeController {
	
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "mySecretKey";
	
	 @Qualifier("viajeRepository")
	 @Autowired
	 private final ViajeRepository repository;
	
	 public ViajeController(@Qualifier("viajeRepository") ViajeRepository repository) {
		 this.repository = repository;
	 }
	 
	//Recupero todos los viajes
	@ApiOperation(value = "Recupero el universo de viajes", response = Viaje.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
	@ApiResponse(code = 401, message = "not authorized!"),
	@ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping	    
    public Iterable<Viaje> getViajes() {
        return repository.findAll();
    }    

    //Permite recuperar todos los viajes realizados
	@ApiOperation(value = "Recupero los viajes realizados de un usuario en particular", response = Viaje.class)
    @GetMapping("/findByRealizados/usuario/{usuario}")
    public List<Viaje> findByRealizados(@PathVariable("usuario") Long usuario) {
        return repository.findByRealizados(usuario);
    }
    
    //Permite recuperar todos los viajes pendientes
	@ApiOperation(value = "Recupero los viajes pendientes de un usuario en particular", response = Viaje.class)
    @GetMapping("/findByPendientes/usuario/{usuario}")
    public List<Viaje> findByPendientes(@PathVariable("usuario") Long usuario) {
        return repository.findByPendientes(usuario);
    }
    
    //Permite recuperar el universo de viajes de un usuario en particular
	@ApiOperation(value = "Recupero el universo de viajes de un usuario", response = Viaje.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
	@ApiResponse(code = 401, message = "not authorized!"),
	@ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/findByUsuario/{usuario}")
    public List<Viaje> findByUsuario(@PathVariable("usuario") Long usuario) {
        return repository.findByUsuario(usuario);
    }
    
    //Permite recuperar las zonas geograficas mas visitadas indicando su nombre y cantidad de viajes
	@ApiOperation(value = "Recupero las zonas geograficas mas visitadas", response = Viaje.class)
    @GetMapping("/findByZonasGeograficasMasVisitadas")
    public List<ReporteZonasVisitadas> findByZonasGeograficasMasVisitadas() {
        return repository.findByZonasGeograficasMasVisitadas();
    }
    
    //Permite recuperar los viajes de un usuario en un rago de fecha determinado
	@ApiOperation(value = "Recupero los viajes de un usuario dentro de un rango de fechas", response = Viaje.class)
    @PostMapping("/findByFechasAndUsuario")
    public List<Viaje> findByFechasAndUsuario(@RequestBody ViajeDTO viaje) throws ParseException {
        return repository.findByFechasAndUsuario(viaje.getFechaInicio(), viaje.getFechaFin(), viaje.getUsuario());
    }
    
    //Permite recuperar los viajes de un usuario filtrados por zona geografica
	@ApiOperation(value = "Recupero los viajes de un usuario a una zona geografica en particular", response = Viaje.class)
    @GetMapping("/findByCiudadDestinoAndUsuario/{ciudad}/{usuario}")
    public List<Viaje> findByCiudadDestinoAndUsuario(@PathVariable("ciudad") String ciudad, @PathVariable("usuario") Long usuario) {
        return repository.findByCiudadDestinoAndUsuario(ciudad, usuario);
    }
    
    //Permite recuperar los usuarios junto con su cantidad de viajes realizados
	@ApiOperation(value = "Recuperar los usuarios junto con su cantidad de viajes realizados", response = Viaje.class)
    @GetMapping("/findByUsuariosConMasViajesRealizados")
    public List<UsuarioDTO> findByUsuariosConMasViajesRealizados(HttpServletRequest request) {
    	RestTemplate restTemplate = new RestTemplate();
    	String uri = "http://localhost:8085/usuarios";	   
    	
    	String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); 
        headers.add("User-Agent", "PostmanRuntime/7.26.3");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + jwtToken);        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);	 
    		    	
    	List<UsuarioPOJO> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UsuarioPOJO>>() {}).getBody(); 
    	List<UsuarioPOJO> usuarios = response;
    	System.out.println("Usuarios ////////////////////");
    	System.out.println(usuarios);	    	
    	List<ReporteUsuariosViajes> reportes = repository.findByUsuariosConMasViajesRealizados();
    	List<UsuarioDTO> usuariosARetornar = new ArrayList<>();
    	for(ReporteUsuariosViajes reporte: reportes) {
    		UsuarioDTO usuario = new UsuarioDTO();
    		for (UsuarioPOJO user: usuarios) {
    			if(reporte.getId().equals(user.getId())) {
    				usuario.setNombre(user.getUsuario());
    				usuario.setViajes(reporte.getViajes());
    				break;
    			}
    		usuariosARetornar.add(usuario);
    		}
    	}
    	
    	return usuariosARetornar;
    }
    
    //Alta
	@ApiOperation(value = "Alta de un viaje", response = Viaje.class)
    @PostMapping("/nuevo")
    public Viaje addViaje(@RequestBody Viaje v) {
        return repository.save(v);
    }

    //Baja
	@ApiOperation(value = "Baja de un viaje", response = Viaje.class)
    @DeleteMapping("/{id}")
    void deleteViaje(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
