package ciu.grupo1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ciu.grupo1.dto.EstadoInscripcionDto;
import ciu.grupo1.dto.InscripcionDto;
import ciu.grupo1.dto.UsuarioRegistroDto;
import ciu.grupo1.model.EstadoInscripcion;
import ciu.grupo1.model.Inscripcion;
import ciu.grupo1.model.TipoEstadoInscripcion;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.repository.EstadoInscripcionRepository;
import ciu.grupo1.repository.InscripcionRepository;
import ciu.grupo1.repository.UsuarioRepository;

@Service
public class InscripcionService {
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EstadoInscripcionRepository estadoInscripcionRepository;
	
	@Transactional(readOnly = true)
	public List<InscripcionDto> getByEmail(String email) {
		
		Usuario usuario= this.usuarioRepository.findByEmail(email).get();
		List<Inscripcion> inscripciones = this.inscripcionRepository.findWithEventoAndUsuarioAndEstadoInscripcionByUsuario(usuario);
		
		inscripciones.forEach(i -> System.out.println(i.getId()));
		
		List<InscripcionDto> inscripcionesDto = inscripciones.stream()
													.map(inscripcion -> inscripcion.toDto())
													.collect(Collectors.toList());
		
		return inscripcionesDto;
		
	}
	
	@Transactional
	public InscripcionDto cambiarEstadoInscripcion(InscripcionDto inscripcionDto) {
		UUID idInscripcion = UUID.fromString(inscripcionDto.getId());
		
		//Busco el estadoInscripcion correspondiente al nombre del estado que tenga EstadoInscripcionDto.
		EstadoInscripcionDto estadoInscripcionDto = inscripcionDto.getEstadoInscripcion();
		TipoEstadoInscripcion tipoEstadoInscripcion = TipoEstadoInscripcion.valueOf(estadoInscripcionDto.getEstado());
		EstadoInscripcion estadoInscripcion = this.estadoInscripcionRepository.findByNombre(tipoEstadoInscripcion).get();
		
		Inscripcion inscripcionACambiar = this.inscripcionRepository.findWithEstadoInscripcionById(idInscripcion).get();
		
		inscripcionACambiar.setEstadoInscripcion(estadoInscripcion);
		
		this.inscripcionRepository.save(inscripcionACambiar);
		
		return inscripcionACambiar.toDto();
	}
}
