package ciu.grupo1.service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ciu.grupo1.dto.EstadoInscripcionDto;
import ciu.grupo1.dto.EventoDto;
import ciu.grupo1.dto.InscripcionDto;
import ciu.grupo1.dto.UsuarioAdminDto;
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
		Usuario usuario= this.usuarioRepository.findByEmail(email).orElse(null);
		List<Inscripcion> inscripciones = this.inscripcionRepository.findWithEventoAndUsuarioAndEstadoInscripcionByUsuario(usuario);		

		List<InscripcionDto> inscripcionesDto = inscripciones.stream()
													.map(inscripcion -> inscripcion.toDto())
													.collect(Collectors.toList());		
		return inscripcionesDto;
		
	}
	
	@SuppressWarnings("unused")
	private InscripcionDto inscripcionADto(Inscripcion inscripcion) {
	    InscripcionDto dto = new InscripcionDto();
	    dto.setId(inscripcion.getId().toString());

	   
	    Usuario Usuario= new Usuario();
		UsuarioAdminDto usuarioDto = new UsuarioAdminDto(Usuario);
	    usuarioDto.setNombre(inscripcion.getUsuario().getNombre());
	    usuarioDto.setApellido(inscripcion.getUsuario().getApellido());
	    usuarioDto.setEmail(inscripcion.getUsuario().getEmail());
	    dto.setUsuario(usuarioDto);


	    EventoDto eventoDto = new EventoDto();
	    eventoDto.setFechaEvento(inscripcion.getEvento().getFechaEvento());
	    eventoDto.setHoraInicio(inscripcion.getEvento().getHoraInicio());
	    eventoDto.setDescripcion(inscripcion.getEvento().getDescripcion());
	    eventoDto.setSala(inscripcion.getEvento().getSala());
	    eventoDto.setCapacidad(inscripcion.getEvento().getCapacidad());
	    dto.setEvento(eventoDto);

	    EstadoInscripcionDto estadoDto = new EstadoInscripcionDto();
	    estadoDto.setEstado(inscripcion.getEstadoInscripcion().getNombre().name());
	    dto.setEstadoInscripcion(estadoDto);

	    return dto;
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

    
    public List<InscripcionDto> getPendientes() {
        return inscripcionRepository.findPendientes()
                .stream()
                .map(this::inscripcionADto) 
                .collect(Collectors.toList());
    }
}
