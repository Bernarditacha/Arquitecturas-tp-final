package com.practico.integrador.dto;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
public class UsuarioDTO {

	String nombre;
	int viajes;
	
}
