package com.practico.integrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practico.integrador.model.Viaje;
import com.practico.integrador.repository.ViajeRepository;
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
	    @GetMapping("/getRealizados/usuario/{usuario}")
	    public List<Viaje> getRealizados(@PathVariable("usuario") Long usuario) {
	        return repository.findByRealizados(usuario);
	    }
	    
	    //Permite recuperar todos los viajes pendientes
	    @GetMapping("/getPendientes/usuario/{usuario}")
	    public List<Viaje> getPendientes(@PathVariable("usuario") Long usuario) {
	        return repository.findByPendientes(usuario);
	    }
	    
	    //Permite recuperar todos los viajes de un usuario en particular
	    @GetMapping("/findByUsuario/{usuario}")
	    public List<Viaje> findByUsuario(@PathVariable("usuario") Long usuario) {
	        return repository.findByUsuario(usuario);
	    }
	    
	    //Permite recuperar las zonas geograficas mas visitadas indicando su nombre y cantidad de viajes
	    @GetMapping("/findByZonasGeograficasMasVisitadas")
	    public List<ReporteZonasVisitadas> findByZonasGeograficasMasVisitadas() {
	        return repository.findByZonasGeograficasMasVisitadas();
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
