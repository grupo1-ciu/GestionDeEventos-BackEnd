package ciu.grupo1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ciu.grupo1.dto.InscripcionDto;
import ciu.grupo1.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
	
	@Autowired
	private InscripcionService inscripcionService;
	
	@GetMapping("/{email}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<InscripcionDto>> findInscripcionesByEmail(@PathVariable String email) {
		List<InscripcionDto> inscripciones = this.inscripcionService.getByEmail(email);
		return ResponseEntity.ok(inscripciones);
	}
}
