package com.practico.integrador.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Vuelo extends Plan{

	@Id
	private long id;
	
	private String descripcion;
	
	private Long usuario;
	
	private int nro_vuelo;
	
	private String compania;
	
	@Column(name="aeropuerto_entrada")
	private String aeropuertoEntrada;
	
	@Column(name="aeropuerto_salida")
	private String aeropuerto_salida;
	
	@Column(name="codigo_reserva")
	private String codigoReserva;
	
	@Column(name="info_avion")
	private String infoAvion;
	
	@Column(name="tiempo_escalas")
	private int tiempoEscalas;

	public Vuelo() {
		super();
	}
	
	public Vuelo(Viaje id_viaje, Timestamp fecha_incio, Timestamp fecha_fin, String nombre, String latitud,
			String longitud) {
		super(id_viaje, fecha_incio, fecha_fin, nombre, latitud, longitud);
	}
	
	public Vuelo(long id, Viaje id_viaje, Timestamp fecha_incio, Timestamp fecha_fin, String nombre, String latitud,
			String longitud, String descripcion, Long usuario, int nro_vuelo, String compania, String aeropuertoEntrada,
			String aeropuerto_salida, String codigoReserva, String info_avion, int tiempoEscalas) {
		super(id_viaje, fecha_incio, fecha_fin, nombre, latitud, longitud);
		this.id = id;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.nro_vuelo = nro_vuelo;
		this.compania = compania;
		this.aeropuertoEntrada = aeropuertoEntrada;
		this.aeropuerto_salida = aeropuerto_salida;
		this.codigoReserva = codigoReserva;
		this.infoAvion = info_avion;
		this.tiempoEscalas = tiempoEscalas;
	}
	
	@Override
	public String toString() {
		return "Vuelo [id=" + id + "descripcion=" + descripcion + ", usuario=" + usuario + ", nro_vuelo=" + nro_vuelo + ", compania="
				+ compania + ", aeropuertoEntrada=" + aeropuertoEntrada + ", aeropuerto_salida=" + aeropuerto_salida
				+ ", codigoReserva=" + codigoReserva + ", info_avion=" + infoAvion + ", tiempoEscalas=" + tiempoEscalas
				+ ", getId_viaje()=" + getViaje() + ", getFechaInicio()=" + getFechaInicio()
				+ ", getFecha_fin()=" + getFechaFin() + ", getNombre()=" + getNombre() + ", getLatitud()="
				+ getLatitud() + ", getLongitud()=" + getLongitud() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}

}
