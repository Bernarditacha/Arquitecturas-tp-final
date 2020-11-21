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
import com.practico.integrador.repository.HotelRepository;

@RestController
@RequestMapping("planes/hoteles")
public class HotelController {

	 @Qualifier("hotelRepository")
	 @Autowired
	 private final HotelRepository repository;

	 public HotelController(@Qualifier("hotelRepository") HotelRepository repository) {
		 this.repository = repository;
	 }
	 
	 	//Recupero todos los hoteles de un viaje
	    @GetMapping	    
	    public Iterable<Hotel> getHoteles() {
	        return repository.findAll();
	    }  
	    
	    
	    //Alta
	    @PostMapping("/nuevo")
	    public Hotel addHotel(@RequestBody Hotel h) {
	        return repository.save(h);
	    }

	    //Baja
	    @DeleteMapping("/{id}")
	    void deleteHotel(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	    
	    //Modificacion
	    @PostMapping("/editar")
	    public Hotel updateHotel(@RequestBody Hotel h) {
	        return repository.save(h);
	    }
}
