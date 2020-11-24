package com.practico.integrador.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class Plan {

	@Id
	private long id;
	
	@ManyToOne
	private Viaje viaje;
	
	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;
	
	@Column(name="fecha_fin")
	private Timestamp fechaFin; 
	
	private String nombre;
	
	private String latitud;
	
	private String longitud;
	
	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Plan(Viaje id_viaje, Timestamp fecha_incio, Timestamp fecha_fin, String nombre, String latitud,
			String longitud) {
		super();
		this.viaje = id_viaje;
		this.fechaInicio = fecha_incio;
		this.fechaFin = fecha_fin;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
}
