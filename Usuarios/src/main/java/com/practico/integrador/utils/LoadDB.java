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
        	//User pass -> 123456789
        	usuarioRepository.save(new Usuario(new Long(1), "user", "25f9e794323b453885f5181f1b624d0b"));
        	//User pass -> 1234
        	usuarioRepository.save(new Usuario(new Long(2), "user1", "81dc9bdb52d04dc20036dbd8313ed055"));
        	
            Iterable<Usuario> usuarios = usuarioRepository.findAll();;
            System.out.println("Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println("\t" + usuario);
            }
            
        };
    }
    
}
