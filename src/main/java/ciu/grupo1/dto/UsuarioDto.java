package ciu.grupo1.dto;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ciu.grupo1.model.Rol;
import ciu.grupo1.model.TipoRol;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.model.UsuarioRol;
import ciu.grupo1.service.RolService;

public class UsuarioDto {
	
	private String nombre;
	private String apellido;
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
			usuario.setRoles(new ArrayList<UsuarioRol>());
		}
		
		return usuario;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
