package ciu.grupo1.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ciu.grupo1.model.Inscripcion;
import ciu.grupo1.repository.EventoRepository;
import ciu.grupo1.repository.InscripcionRepository;
import ciu.grupo1.repository.UsuarioRepository;

@Service
public class InscripcionService {
	

	    @Autowired
	    private InscripcionRepository inscripcionRepository;

	    @Autowired
	    private EventoRepository eventoRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	
	    public boolean estaInscrito(UUID idUsuario, UUID idEvento) {
	        return inscripcionRepository.existsByUsuarioIdAndEventoId(idUsuario, idEvento);
	    }

	   
	    public int contarInscripciones(UUID idEvento) {
	        return inscripcionRepository.countByEventoId(idEvento);
	    }

	    
	    public void guardarInscripcion(Inscripcion inscripcion) {
	        inscripcionRepository.save(inscripcion);
	    }
}

