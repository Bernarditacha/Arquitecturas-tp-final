package com.practico.integrador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/*import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;*/

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;*/
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
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
import org.mockito.junit.MockitoRule;

import com.practico.integrador.model.Usuario;
import com.practico.integrador.repository.UsuarioRepository;

import junit.framework.Assert;








@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTests {
	
	@Mock(stubOnly = true)
	UsuarioRepository usuarioRepository;	
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	ArrayList<Usuario> savedUsers;
	
	Usuario[] users;
	
	@Before
    public void setup() { 
		
		savedUsers = new ArrayList<>();
		users = new Usuario[] { new Usuario((long) 12345 ,"Alejandro", "21"), new Usuario((long) 123456, "Lucía", "45646")};
	}
	
	@Test
	public void userEquals() {
		System.out.print("Test one");
		Usuario userAdd = new Usuario((long) 156234, "juan", "123456");
		Usuario userTest = new Usuario((long) 156234, "juan", "123456");
		assertEquals(userTest.getUsuario().toString(), userAdd.getUsuario().toString()); 
	}
	
	@Test
	public void userNotEquals() {
		System.out.print("Test two");
		Usuario userAdd = new Usuario((long) 156234, "pedro", "123456");
		Usuario userTest = new Usuario((long) 156234, "manuel", "123456");
		assertEquals(userTest.getUsuario().toString(), userAdd.getUsuario().toString()); 
	}
	
	@Test
	public void userPassCompare() {
		System.out.print("Test three");
		Usuario userAdd = new Usuario((long) 156234, "pedro", "123456000");
		Usuario userTest = new Usuario((long) 156234, "manuel", "123456");
		assertEquals(userTest.getContrasenia().toString(), userAdd.getContrasenia().toString()); 
	}

	

}
