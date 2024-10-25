package ciu.grupo1.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ciu.grupo1.model.Usuario;

public class UserInfoDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username; // Changed from 'name' to 'username' for clarity
	private String password;
	private String nombre;
	private String apellido;
	private List<GrantedAuthority> authorities;
	private List<String> roles;

	public UserInfoDetails(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.username = usuario.getEmail(); // Assuming 'email' is used as 'username'
		this.password = usuario.getPassword();
		
		this.authorities = usuario.getUsuarioRoles().stream().map(usuarioRol -> usuarioRol.getRol().getNombre().toString())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
		this.roles = new ArrayList<String>();
		usuario.getUsuarioRoles().stream()
			.map(usuarioRol -> this.roles.add
					(usuarioRol.getRol().getNombre().toString()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isEnabled() {
		return true; // Implement your logic if you need this
	}
}
