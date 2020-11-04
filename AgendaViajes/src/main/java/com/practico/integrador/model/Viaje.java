package com.practico.integrador.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Viaje {

	@Id
	private Long id;
	
	private String nombre;
	
	@Column(name="ciudad_destino")
	private String ciudadDestino;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;
	
	@Column(name="fecha_fin")
	private Timestamp fechaFin;
	
	private String descripcion;
	
	private Long usuario;
	
	public Viaje() {
		super();
	}

	public Viaje(Long id, String nombre, String ciudadDestino, Timestamp fechaInicio, Timestamp fechaFin,
			String descripcion, Long usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudadDestino = ciudadDestino;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", nombre=" + nombre + ", ciudadDestino=" + ciudadDestino + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", descripcion=" + descripcion + ", usuario=" + usuario + "]";
	}

}
