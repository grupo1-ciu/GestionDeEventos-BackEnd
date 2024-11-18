package ciu.grupo1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ciu.grupo1.model.Inscripcion;
import java.util.List;
import ciu.grupo1.model.Usuario;


public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID>{
	
	public List<Inscripcion> findByUsuario(Usuario usuario);
	
	public abstract boolean existsByUsuario_IdAndEvento_Id(UUID idUsuario, UUID idEvento);


	
	long countByEventoId(UUID idEvento);
	
	@EntityGraph(value = "InscripcionesWithEventoAndUsuarioAndEstadoInscripcion")
	public List<Inscripcion> findWithEventoAndUSuarioAndEstadoInscripcionByUsuario(Usuario usuario);
}
