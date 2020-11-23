package com.practico.integrador.controller;

import java.text.ParseException;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("planes/hoteles")
@Api(value = "HotelControllerJpa", description = "REST API Reserva de hoteles")
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
	 
	 	//Recupero todas las reservas de hotel de un viaje
		@ApiOperation(value = "Recupero las reservas de hotel de un viaje en particular", response = Hotel.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
		@ApiResponse(code = 401, message = "not authorized!"),
		@ApiResponse(code = 403, message = "forbidden!!!"),
		@ApiResponse(code = 404, message = "not found!!!") })
	    @GetMapping("/findByViaje/{viaje}")
	    public Iterable<Hotel> findByViaje(@PathVariable("viaje") Long viaje) {
	        return repository.findByViaje_Id(viaje);
	    }  
	    
	    //Permite dar de alta una reserva de hotel sin indicar a que viaje pertenece, guiandose por la fecha de inicio y fin
		@ApiOperation(value = "Permite dar de alta una reserva de hotel sin indicar el viaje al que pertenece", response = Hotel.class)
		@PostMapping("/nuevo-archivo")
	    public Hotel addHotelSegunFechas(@RequestBody HotelDTO h) throws ParseException {        	
	    	Viaje viaje = viajeRepository.findByFechasAndUsuario(h.getFechaIncio(), h.getFechaFin(), h.getUsuario()).get(0);
	        Hotel hotel = new Hotel(h.getId(), viaje, h.getFechaIncio(), h.getFechaFin(), h.getNombre(), h.getLatitud(),
	    			h.getLongitud(), h.getNombreHotel(), h.getDireccion(), h.getTipoHabitacion());
	    	return repository.save(hotel);
	    }
	    
	    //Alta
		@ApiOperation(value = "Alta de una reserva de hotel", response = Hotel.class)
	    @PostMapping("/nuevo")
	    public Hotel addHotel(@RequestBody Hotel h) {
	        return repository.save(h);
	    }

	    //Baja
		@ApiOperation(value = "Baja de una reserva de hotel", response = Hotel.class)
	    @DeleteMapping("/{id}")
	    void deleteHotel(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	    
	    //Modificacion
		@ApiOperation(value = "Edicion de una reserva de hotel", response = Hotel.class)
	    @PostMapping("/editar")
	    public Hotel updateHotel(@RequestBody Hotel h) {
	        return repository.save(h);
	    }
}
