package ciu.grupo1.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
	    Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
	    if (usuarioOpt.isEmpty()) {
	        throw new IllegalArgumentException("Usuario con email " + email + " no encontrado");
	    }

	    Usuario usuario = usuarioOpt.get();
	    System.out.println("Usuario ID: " + usuario.getId() + ", Email: " + usuario.getEmail());

	    List<Inscripcion> inscripciones = inscripcionRepository.findWithEventoAndUSuarioAndEstadoInscripcionByUsuario(usuario);

	    if (inscripciones.isEmpty()) {
	        System.out.println("No se encontraron inscripciones para el usuario.");
	    } else {
	        inscripciones.forEach(i -> System.out.println("Inscripción ID: " + i.getId()));
	    }

	    return inscripciones.stream()
	                        .map(Inscripcion::toDto)
	                        .collect(Collectors.toList());
	}
	
    @Transactional(readOnly = true)
    public boolean estaInscrito(UUID uuid, UUID idEvento) {
        return inscripcionRepository.existsByUsuario_IdAndEvento_Id(uuid, idEvento);
    }
	

	@Transactional
	public void guardarInscripcion(Inscripcion inscripcion) {
	  
	    boolean existe = inscripcionRepository.existsByUsuario_IdAndEvento_Id(inscripcion.getUsuario().getId(), inscripcion.getEvento().getId());
	    if (existe) {
	        throw new IllegalArgumentException("El usuario ya está inscrito en este evento");
	    }

	    inscripcionRepository.save(inscripcion);
	}
	
    public long contarInscripciones(UUID idEvento) {
        return inscripcionRepository.countByEventoId(idEvento);
    }
   
}
