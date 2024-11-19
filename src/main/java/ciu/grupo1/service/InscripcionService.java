package ciu.grupo1.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ciu.grupo1.dto.InscripcionDto;
import ciu.grupo1.model.Inscripcion;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.repository.InscripcionRepository;
import ciu.grupo1.repository.UsuarioRepository;

@Service
public class InscripcionService {
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = true)
	public List<InscripcionDto> getByEmail(String email) {
		Usuario usuario= this.usuarioRepository.findByEmail(email).orElse(null);
		List<Inscripcion> inscripciones = this.inscripcionRepository.findWithEventoAndUsuarioAndEstadoInscripcionByUsuario(usuario);		
		List<InscripcionDto> inscripcionesDto = inscripciones.stream()
													.map(inscripcion -> inscripcion.toDto())
													.collect(Collectors.toList());		
		return inscripcionesDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<InscripcionDto> getAceptadasYPendientesByEmail(String email) {
		Usuario usuario = this.usuarioRepository.findByEmail(email).orElse(null);
		List<Inscripcion> inscripciones = this.inscripcionRepository.findPendientesAndAceptadasByUsuario(usuario);
		List<InscripcionDto> inscripcionesDto = inscripciones.stream()
													.map(i -> i.toDto())
													.collect(Collectors.toList());
		return inscripcionesDto;
	}
}
