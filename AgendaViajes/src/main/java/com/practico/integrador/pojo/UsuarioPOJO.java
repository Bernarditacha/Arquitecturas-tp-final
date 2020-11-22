package com.practico.integrador.pojo;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
public class UsuarioPOJO {

	long id;
	String usuario;
	
}
