package com.practico.integrador.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.practico.integrador.dto.HotelDTO;
import com.practico.integrador.model.Hotel;
import com.practico.integrador.model.Viaje;
import com.practico.integrador.repository.HotelRepository;
import com.practico.integrador.repository.ViajeRepository;

@RestController
@RequestMapping("planes/hoteles")
public class HotelController {

	 @Qualifier("hotelRepository")
	 @Autowired
	 private final HotelRepository repository;

	 @Qualifier("viajeRepository")
	 @Autowired
	 private final ViajeRepository viajeRepository;
	 
	 public HotelController(@Qualifier("hotelRepository") HotelRepository repository, @Qualifier("viajeRepository") ViajeRepository viajeRepository ) {
		 this.repository = repository;
		 this.viajeRepository = viajeRepository;
	 }
	 
	 	//Recupero todos los hoteles de un viaje
	    @GetMapping	    
	    public Iterable<Hotel> getHoteles() {
	        return repository.findAll();
	    }  
	    
	    //Permite dar de alta una reserva de hotel sin indicar a que viaje pertenece, guiandose por la fecha de inicio y fin
	    @PostMapping("/nuevo-archivo")
	    public Hotel addHotelSegunFechas(@RequestBody HotelDTO h) throws ParseException {        	
	    	Viaje viaje = viajeRepository.findByFechasAndUsuario(h.getFechaIncio(), h.getFechaFin(), h.getUsuario()).get(0);
	        Hotel hotel = new Hotel(h.getId(), viaje, h.getFechaIncio(), h.getFechaFin(), h.getNombre(), h.getLatitud(),
	    			h.getLongitud(), h.getNombreHotel(), h.getDireccion(), h.getTipoHabitacion());
	    	return repository.save(hotel);
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
