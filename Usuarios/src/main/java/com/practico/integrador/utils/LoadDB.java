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
        	usuarioRepository.save(new Usuario(new Long(1), "user", "1a1dc91c907325c69271ddf0c944bc72"));
        	usuarioRepository.save(new Usuario(new Long(2), "user1", "a722c63db8ec8625af6cf71cb8c2d939"));
        	
            Iterable<Usuario> usuarios = usuarioRepository.findAll();;
            System.out.println("Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println("\t" + usuario);
            }
            
        };
    }
    
}
