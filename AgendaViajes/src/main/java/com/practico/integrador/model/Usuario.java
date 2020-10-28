package com.practico.integrador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	private Long id;

	private String usuario;
	private String contrasenia;
	
	public Usuario() {
		super();
	}

	public Usuario(Long id, String usuario, String contrasenia) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + "]";
	}

}
