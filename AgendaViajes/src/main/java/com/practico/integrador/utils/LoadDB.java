package com.practico.integrador.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.practico.integrador.model.Hotel;
import com.practico.integrador.model.Viaje;
import com.practico.integrador.model.Vuelo;
import com.practico.integrador.repository.HotelRepository;
import com.practico.integrador.repository.ViajeRepository;
import com.practico.integrador.repository.VueloRepository;

@Configuration
public class LoadDB {

	
    @Bean
    CommandLineRunner initDatabase(@Qualifier("viajeRepository") ViajeRepository viajeRepository, @Qualifier("vueloRepository") VueloRepository vueloRepository, @Qualifier("hotelRepository") HotelRepository hotelRepository) {
        return args -> {
              	
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
        
            
        	//Creacion de plan vuelo para viaje 1
            Viaje viaje1 = viajeRepository.findById(new Long(1)).get();
                      
        	Date dateInicioV1 = dateFormat.parse("1/11/2020");	//8:35:00 PM
        	long timeInicioRV1 = dateInicioV1.getTime();
        	Timestamp inicioV1 = new Timestamp(timeInicioRV1);
        	
        	Date dateFinV1 = dateFormat.parse("1/11/2020");		//17:00:00 PM
        	long timeFinV1 = dateFinV1.getTime();
        	Timestamp finV1 = new Timestamp(timeFinV1);
        	
            vueloRepository.save(new Vuelo(new Long(1), viaje1, inicioV1, finV1, "Primer vuelo", "6.217",
        			"-75.567 6° 13′ 1″", "Vuelo en business", new Long(1), 1, "Aerolineas Argentinas","Olaya Herrera",
        			"Ministro Pistarini", "C502", "Airbus A380", 0));
          
           //Creacion de plan hotel para viaje 1
        	Date dateInicioH1 = dateFormat.parse("1/11/2020");		//18:00:00 PM
        	long timeInicioH1 = dateInicioH1.getTime();
        	Timestamp inicioH1 = new Timestamp(timeInicioH1);
        	
        	Date dateFinH1 = dateFormat.parse("2/12/2020 ");		//16:00:00 PM
        	long timeFinH1 = dateFinV1.getTime();
        	Timestamp finH1 = new Timestamp(timeFinH1);
        	
            hotelRepository.save(new Hotel(new Long(1), viaje1, inicioH1, finH1, "Hotel 5 estrellas", "6.25094938278198",
			"-75.5629577636719", "Gran hotel" , "Calle 54 N° 45-92 Av. Oriental x Caracas", "Matrimonial"));
            
            
            //Para probar zona geografica mas visitada
            viajeRepository.save(new Viaje(new Long(4), "Vacaciones verano", "Medellin", inicioP, finP, "Viaje con amigos", new Long(4)));

        };
    }
    
}
