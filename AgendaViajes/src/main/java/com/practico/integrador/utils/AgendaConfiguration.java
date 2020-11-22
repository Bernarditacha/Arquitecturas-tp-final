package com.practico.integrador.utils;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true) //Necesario para que funcione la anotación en el servicio oldman 
public class AgendaConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Desactiva el método por defecto
		http.csrf().disable()
		    //Agrega el método de filtrado que codificamos nosotros
			//authorizeRequests().
				
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()
			.antMatchers(HttpMethod.GET, "/viajes").permitAll()
			.anyRequest().authenticated();
	}
}
