package ciu.grupo1.dto;

import java.util.HashSet;
import java.util.UUID;

import ciu.grupo1.model.Usuario;
import ciu.grupo1.model.UsuarioRol;

public class UsuarioRegistroDto extends UsuarioDto{
	
	private String email;
	private String password;
	
	public Usuario toModel(boolean esNuevo) {
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		if(esNuevo) {
			usuario.setId(UUID.randomUUID());
			usuario.setUsuarioRoles(new HashSet<UsuarioRol>());
		}
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
