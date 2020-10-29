package com.practico.integrador.utils;

public class ResponseError extends Exception{
	 
	private String estado;
	private int codigo;
	private String mensaje;
	
	public ResponseError(String estado, int codigo, String mensaje) {
		this.estado = estado;
		this.codigo = codigo;
		this.mensaje = mensaje;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "ResponseError [estado=" + estado + ", codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}

	
}