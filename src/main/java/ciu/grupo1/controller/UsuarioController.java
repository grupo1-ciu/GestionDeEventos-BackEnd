package ciu.grupo1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ciu.grupo1.dto.UsuarioAdminDto;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UsuarioAdminDto> findAll() {
		List<Usuario> usuarios = this.usuarioService.getUsuarios();
		List<UsuarioAdminDto> usuariosDto = usuarios.stream()
												.map(usuario -> new UsuarioAdminDto(usuario))
												.collect(Collectors.toList());
		return usuariosDto;
	}
	
	@GetMapping("/{email}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UsuarioAdminDto findByEmail(@PathVariable String email) {
		Usuario usuario = this.usuarioService.getUsuarioWithInscripciones(email);
		UsuarioAdminDto usuarioDto = new UsuarioAdminDto(usuario);
		return usuarioDto;
	}
}
