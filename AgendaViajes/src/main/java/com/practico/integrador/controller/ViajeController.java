package com.practico.integrador.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("viajes")
public class ViajeController {

	 @Qualifier("viajeRepository")
	 @Autowired
	 private final ViajeRepository repository;

	 public ViajeController(@Qualifier("viajeRepository") ViajeRepository repository) {
		 this.repository = repository;
	 }
	 
	 	//Recupero todos los viajes
	    @GetMapping	    
	    public Iterable<Viaje> getViajes() {
	        return repository.findAll();
	    }    

	    //Permite recuperar todos los viajes realizados
	    @GetMapping("/findByRealizados/usuario/{usuario}")
	    public List<Viaje> findByRealizados(@PathVariable("usuario") Long usuario) {
	        return repository.findByRealizados(usuario);
	    }
	    
	    //Permite recuperar todos los viajes pendientes
	    @GetMapping("/findByPendientes/usuario/{usuario}")
	    public List<Viaje> findByPendientes(@PathVariable("usuario") Long usuario) {
	        return repository.findByPendientes(usuario);
	    }
	    
	    //Permite recuperar el universo de viajes de un usuario en particular
	    @GetMapping("/findByUsuario/{usuario}")
	    public List<Viaje> findByUsuario(@PathVariable("usuario") Long usuario) {
	        return repository.findByUsuario(usuario);
	    }
	    
	    //Permite recuperar las zonas geograficas mas visitadas indicando su nombre y cantidad de viajes
	    @GetMapping("/findByZonasGeograficasMasVisitadas")
	    public List<ReporteZonasVisitadas> findByZonasGeograficasMasVisitadas() {
	        return repository.findByZonasGeograficasMasVisitadas();
	    }
	    
	    //Permite recuperar los viajes de un usuario en un rago de fecha determinado
	    @PostMapping("/findByFechasAndUsuario")
	    public List<Viaje> findByFechasAndUsuario(@RequestBody ViajeDTO viaje) throws ParseException {
	        return repository.findByFechasAndUsuario(viaje.getFechaInicio(), viaje.getFechaFin(), viaje.getUsuario());
	    }
	    
	    //Permite recuperar los viajes de un usuario filtrados por zona geografica
	    @GetMapping("/findByCiudadDestinoAndUsuario/{ciudad}/{usuario}")
	    public List<Viaje> findByCiudadDestinoAndUsuario(@PathVariable("ciudad") String ciudad, @PathVariable("usuario") Long usuario) {
	        return repository.findByCiudadDestinoAndUsuario(ciudad, usuario);
	    }
	    
	    //Permite recuperar los usuarios junto con su cantidad de viajes realizados
	    @GetMapping("/findByUsuariosConMasViajesRealizados")
	    public List<UsuarioDTO> findByUsuariosConMasViajesRealizados() {
	    	RestTemplate restTemplate = new RestTemplate();
	    	String uri = "https://localhost:8085/usuarios";	    	
	    		    	
	    	ResponseEntity<List<UsuarioPOJO>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<UsuarioPOJO>>() {});
	    	List<UsuarioPOJO> usuarios = response.getBody();
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
	    @PostMapping("/nuevo")
	    public Viaje addViaje(@RequestBody Viaje v) {
	        return repository.save(v);
	    }

	    //Baja
	    @DeleteMapping("/{id}")
	    void deleteViaje(@PathVariable Long id) {
	        repository.deleteById(id);
	    }

}
