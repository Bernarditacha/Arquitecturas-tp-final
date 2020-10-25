package com.practico.integrador.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;

@Configuration
public class LoadDB {

	
    @Bean
    CommandLineRunner initDatabase(@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
        return args -> {
        	
        	//Creacion de usuarios
        	usuarioRepository.save(new Usuario("user", "pass"));
            
            Iterable<Usuario> usuarios = usuarioRepository.findAll();;
            System.out.println("Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println("\t" + usuario);
            }
        };
    }
    
}
