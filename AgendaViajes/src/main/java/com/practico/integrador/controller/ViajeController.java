package com.practico.integrador.controller;

import java.sql.Date;
import java.util.List;

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
import com.practico.integrador.model.Viaje;
import com.practico.integrador.repository.UsuarioRepository;
import com.practico.integrador.repository.ViajeRepository;

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
	    @GetMapping("/getRealizados")
	    public List<Viaje> getRealizados() {
	        return repository.getRealizados();
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
