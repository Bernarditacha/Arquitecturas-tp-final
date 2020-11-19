package com.practico.integrador;

import static org.junit.Assert.assertTrue;

/*import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;*/

import java.util.ArrayList;

import org.aspectj.lang.annotation.Before;
/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;*/
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = UsuariosApplication.class)
class UsuariosApplicationTests {
	
	@Mock(stubOnly = true)
	UsuarioRepository usuarioRepository;	
	
	ArrayList<Usuario> savedUsers;
	
	@Before(value = "")
    public void setup() {
		savedUsers = new ArrayList<>();
		Mockito.when(usuarioRepository.findAll()).then((el) -> savedUsers);
	}
	 
	@Test
    public void testAddNullUser() {
        try {
        	usuarioRepository.save(null);
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }
	

	

}
