package com.practico.integrador.utils;

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
        	usuarioRepository.save(new Usuario(new Long(1), "user", "pass"));
        	usuarioRepository.save(new Usuario(new Long(2), "bren", "bren01"));
        	usuarioRepository.save(new Usuario(new Long(3), "ger", "ger01"));
        	usuarioRepository.save(new Usuario(new Long(4), "berni", "berni01"));
        	
            Iterable<Usuario> usuarios = usuarioRepository.findAll();;
            System.out.println("Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println("\t" + usuario);
            }
            
        };
    }
    
}
