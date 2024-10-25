package ciu.grupo1.dto;

import java.util.List;

public class UsuarioLoginDto extends UsuarioDto {
	private String token;
	private List<String> roles;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getRoles() { 
		return roles;
	}
}
