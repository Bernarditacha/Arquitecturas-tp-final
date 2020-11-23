package com.practico.integrador.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
public class HotelDTO {

	private long id;
	private Timestamp fechaIncio;
	private Timestamp fechaFin;
	private String nombre;
	private String latitud;
	private String longitud;
	private String nombreHotel;
	private String direccion;
	private String tipoHabitacion;
	private long usuario;

}
