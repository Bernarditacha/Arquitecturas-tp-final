package com.practico.integrador.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	
	@Column(name="ciudad_destino")
	private String ciudadDestino;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;
	
	@Column(name="fecha_fin")
	private Timestamp fechaFin;
	
	private String descripcion;
	
	@ManyToOne
	private Usuario usuario;
	
	public Viaje() {
		super();
	}

	public Viaje(String nombre, String ciudadDestino, Timestamp fechaInicio, Timestamp fechaFin,
			String descripcion, Usuario usuario) {
		super();
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
				+ fechaInicio + ", fechaFin=" + fechaFin + ", descripcion=" + descripcion +  ", usuario=" + usuario + "]";
	}

}
