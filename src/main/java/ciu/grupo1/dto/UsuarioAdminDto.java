package ciu.grupo1.dto;

import ciu.grupo1.model.Usuario;

public class UsuarioAdminDto extends UsuarioDto{
	
	private String email;
	
	public UsuarioAdminDto(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.email = usuario.getEmail();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
