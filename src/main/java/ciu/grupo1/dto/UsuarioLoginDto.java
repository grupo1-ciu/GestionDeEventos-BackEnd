package ciu.grupo1.dto;

import java.util.List;

public class UsuarioLoginDto extends UsuarioDto {
	private String email;
	private String token;
	private List<String> roles;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
