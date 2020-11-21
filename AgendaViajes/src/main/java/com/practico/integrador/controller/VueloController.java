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

import com.practico.integrador.model.Vuelo;
import com.practico.integrador.repository.VueloRepository;

@RestController
@RequestMapping("planes/vuelos")
public class VueloController {

	 @Qualifier("vueloRepository")
	 @Autowired
	 private final VueloRepository repository;

	 public VueloController(@Qualifier("vueloRepository") VueloRepository repository) {
		 this.repository = repository;
	 }
	 
	 	//Recupero todos los vuelos de un viaje
	    @GetMapping	    
	    public Iterable<Vuelo> getHoteles() {
	        return repository.findAll();
	    }  
	    
	    //Alta
	    @PostMapping("/nuevo")
	    public Vuelo addVuelo(@RequestBody Vuelo v) {
	        return repository.save(v);
	    }

	    //Baja
	    @DeleteMapping("/{id}")
	    void deleteVuelo(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	    
	    //Modificacion
	    @PostMapping("/editar")
	    public Vuelo updateVuelo(@RequestBody Vuelo v) {
	        return repository.save(v);
	    }
}
