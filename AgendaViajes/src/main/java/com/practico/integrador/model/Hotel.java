package com.practico.integrador.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Hotel extends Plan {

	@Id
	private long id;

	@Column(name = "nombre_hotel")
	private String nombreHotel;

	private String direccion;

	@Column(name = "tipo_habitacion")
	private String tipoHabitacion;

	
	public Hotel() {
		super();
	}

	public Hotel(Viaje id_viaje, Timestamp fecha_incio, Timestamp fecha_fin, String nombre, String latitud,
			String longitud) {
		super(id_viaje, fecha_incio, fecha_fin, nombre, latitud, longitud);
	}
	
	public Hotel(long id, Viaje id_viaje, Timestamp fecha_incio, Timestamp fecha_fin, String nombre, String latitud,
			String longitud, String nombreHotel, String direccion, String tipoHabitacion) {
		super(id_viaje, fecha_incio, fecha_fin, nombre, latitud, longitud);
		this.id = id;
		this.nombreHotel = nombreHotel;
		this.direccion = direccion;
		this.tipoHabitacion = tipoHabitacion;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombreHotel=" + nombreHotel + ", direccion=" + direccion + ", tipoHabitacion="
				+ tipoHabitacion + ", getId()=" + getId() + ", getNombreHotel()=" + getNombreHotel()
				+ ", getDireccion()=" + getDireccion() + ", getTipoHabitacion()=" + getTipoHabitacion()
				+ ", hashCode()=" + hashCode() + ", getViaje()=" + getViaje() + ", getFechaInicio()=" + getFechaInicio()
				+ ", getFechaFin()=" + getFechaFin() + ", getNombre()=" + getNombre() + ", getLatitud()=" + getLatitud()
				+ ", getLongitud()=" + getLongitud() + "]";
	}

}
