package com.practico.integrador.utils;

import java.sql.Timestamp;
import java.util.Calendar;
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
      
        	//Borro datos de las talas
        	//viajeRepository.deleteAll();
        	
            //Creacion de viajes
           
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();

            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.YEAR, 2019);
            Timestamp inicio = new Timestamp(now.getTime());
            Timestamp fin = new Timestamp (25/12/2020);

            viajeRepository.save(new Viaje("Vacaciones verano", "Medellin", inicio, fin, "Viaje con amigos"));
  
        };
    }
    
}
