package com.practico.integrador;

import static org.junit.Assert.assertTrue;

/*import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;*/

import java.util.ArrayList;

import org.junit.Before;
/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;*/
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;*/

import com.practico.integrador.controller.UsuarioController;
import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;







@RunWith(MockitoJUnitRunner.class)
class UsuarioRepositoryTests {
	
	@Mock(stubOnly = true)
	UsuarioRepository usuarioRepository;	
	
	@InjectMocks
    UsuarioController userService;
	
	ArrayList<Usuario> savedUsers;
	
	Usuario[] users;
	
	@Before
    public void setup() { 
		System.out.println("paso");
		userService.addUsuario(new Usuario((long) 999 ,"Alejandro", "21"));
		savedUsers = new ArrayList<>();
		Mockito.when(usuarioRepository.findAll()).then((el) -> savedUsers);
		
		savedUsers = new ArrayList<>();
		users = new Usuario[] { new Usuario((long) 12345 ,"Alejandro", "21"), new Usuario((long) 123456, "Lucía", "45646")};
		
		Mockito.when(usuarioRepository.save(new Usuario((long) 12345 ,"Alejandro", "21"))).then((el) -> {
            synchronized (savedUsers) {
                savedUsers.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });
		
	}
	 
	@Test
    public void testAddNullUser() {
		userService.addUsuario(new Usuario((long) 999 ,"Alejandro", "21"));
		try {
			Usuario user = new Usuario((long) 156234, "otro", "123456");
			userService.addUsuario(user);
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }
	

	

}
