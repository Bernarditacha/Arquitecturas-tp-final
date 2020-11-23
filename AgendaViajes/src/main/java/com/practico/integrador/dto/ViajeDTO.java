package com.practico.integrador.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
public class ViajeDTO {

	Timestamp fechaInicio;
	Timestamp fechaFin;
	Long usuario;
	
}
