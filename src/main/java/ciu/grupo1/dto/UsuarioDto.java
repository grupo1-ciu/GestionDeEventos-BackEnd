package ciu.grupo1.dto;

import ciu.grupo1.model.Usuario;

public class UsuarioDto {
	
	protected String nombre;
	protected String apellido;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
