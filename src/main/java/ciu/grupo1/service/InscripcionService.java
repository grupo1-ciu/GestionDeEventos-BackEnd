package ciu.grupo1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ciu.grupo1.dto.InscripcionDto;
import ciu.grupo1.dto.UsuarioRegistroDto;
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
		
		Usuario usuario= this.usuarioRepository.findByEmail(email).get();
		System.out.println(usuario.getId());
		System.out.println(usuario.getEmail());
		List<Inscripcion> inscripciones = this.inscripcionRepository.findWithEventoAndUSuarioAndEstadoInscripcionByUsuario(usuario);
		
		System.out.println(inscripciones.size());
		inscripciones.forEach(i -> System.out.println(i.getId()));
		
		List<InscripcionDto> inscripcionesDto = inscripciones.stream()
													.map(inscripcion -> inscripcion.toDto())
													.collect(Collectors.toList());
		
		return inscripcionesDto;
		
	}
}
