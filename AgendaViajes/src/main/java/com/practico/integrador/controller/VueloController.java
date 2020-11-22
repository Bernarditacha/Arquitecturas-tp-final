package com.practico.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practico.integrador.model.Hotel;
import com.practico.integrador.model.Vuelo;
import com.practico.integrador.repository.VueloRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("planes/vuelos")
@Api(value = "VueloControllerJpa", description = "REST API Vuelos")
public class VueloController {

	 @Qualifier("vueloRepository")
	 @Autowired
	 private final VueloRepository repository;

	 public VueloController(@Qualifier("vueloRepository") VueloRepository repository) {
		 this.repository = repository;
	 }
	 
	 	//Recupero todos los vuelos de un viaje
		@ApiOperation(value = "Recupero los vuelos de un viaje en particular", response = Vuelo.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
		@ApiResponse(code = 401, message = "not authorized!"),
		@ApiResponse(code = 403, message = "forbidden!!!"),
		@ApiResponse(code = 404, message = "not found!!!") })
	    @GetMapping("/findByViaje/{viaje}")
	    public Iterable<Vuelo> findByViaje(@PathVariable("viaje") Long viaje) {
	        return repository.findByViaje_Id(viaje);
	    }  
	    
	    //Alta
		@ApiOperation(value = "Permite dar de alta un vuelo", response = Vuelo.class)
	    @PostMapping("/nuevo")
	    public Vuelo addVuelo(@RequestBody Vuelo v) {
	        return repository.save(v);
	    }

	    //Baja
		@ApiOperation(value = "Permite dar de baja un vuelo", response = Vuelo.class)
	    @DeleteMapping("/{id}")
	    void deleteVuelo(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	    
	    //Modificacion
		@ApiOperation(value = "Permite editar un vuelo", response = Vuelo.class)
	    @PostMapping("/editar")
	    public Vuelo updateVuelo(@RequestBody Vuelo v) {
	        return repository.save(v);
	    }
}
