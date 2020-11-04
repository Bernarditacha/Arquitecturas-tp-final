package com.practico.integrador.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.practico.integrador.model.Viaje;
import com.practico.integrador.repository.ViajeRepository;

@Configuration
public class LoadDB {

	
    @Bean
    CommandLineRunner initDatabase(@Qualifier("viajeRepository") ViajeRepository viajeRepository) {
        return args -> {
      
        	//Borro datos de las tablas
        	viajeRepository.deleteAll();
        	
            //Creacion de viajes
           
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	
        	//Viaje pendiente
        	Date dateInicioP = dateFormat.parse("1/11/2020");
        	long timeInicioP = dateInicioP.getTime();
        	Timestamp inicioP = new Timestamp(timeInicioP);
        	
        	Date dateFinP = dateFormat.parse("1/12/2020");
        	long timeFinP = dateFinP.getTime();
        	Timestamp finP = new Timestamp(timeFinP);
        	
            viajeRepository.save(new Viaje(new Long(1), "Vacaciones verano", "Medellin", inicioP, finP, "Viaje con amigos", new Long(4)));
            
            //Viaje realizado
        	Date dateInicioR = dateFormat.parse("1/11/2020");
        	long timeInicioR = dateInicioR.getTime();
        	Timestamp inicioR = new Timestamp(timeInicioR);
        	
        	Date dateFinR = dateFormat.parse("2/11/2020");
        	long timeFinR = dateFinR.getTime();
        	Timestamp finR = new Timestamp(timeFinR);
        	
            viajeRepository.save(new Viaje(new Long(2), "Trabajo", "Buenos Aires", inicioR, finR, "Conferencia presencial", new Long(4)));
  
            //Otro usuario
            viajeRepository.save(new Viaje(new Long(3), "Travel hawai", "Hawai", inicioP, finP, "Vacaciones con familia", new Long(3)));

            Iterable<Viaje> viajes = viajeRepository.findAll();;
            System.out.println("Viajes:");
            for (Viaje viaje : viajes) {
                System.out.println("\t" + viaje);
            }
        };
    }
    
}
