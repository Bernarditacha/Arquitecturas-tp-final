package com.practico.integrador.utils;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true) //Necesario para que funcione la anotación en el servicio oldman 
public class AgendaConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Desactiva el método por defecto
		http.cors();
		http.csrf().disable()
		    //Agrega el método de filtrado que codificamos nosotros
			//authorizeRequests().
				
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui/**", "/webjars/**").permitAll()
			.antMatchers(HttpMethod.GET, "/viajes/**").permitAll()
			.anyRequest().authenticated();
		
		//getCorsRegistry().addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE");
	}
	
	
	
}
