package ciu.grupo1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ciu.grupo1.model.Inscripcion;
import java.util.List;
import java.util.Optional;

import ciu.grupo1.model.Usuario;


public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID>{
	
	public List<Inscripcion> findByUsuario(Usuario usuario);
	
	public Optional<Inscripcion> findById(UUID id);
	
	@EntityGraph(value = "InscripcionesWithEventoAndUsuarioAndEstadoInscripcion")
	public List<Inscripcion> findWithEventoAndUsuarioAndEstadoInscripcionByUsuario(Usuario usuario);
	

	
	@EntityGraph(value = "InscripcionesWithEventoAndUsuarioAndEstadoInscripcion")
	@Query("SELECT i FROM Inscripcion i "
			+ "WHERE (i.estadoInscripcion.nombre = ACEPTADA OR i.estadoInscripcion.nombre = PENDIENTE) "
			+ "AND i.usuario = :usuario")
	public List<Inscripcion> findPendientesAndAceptadasByUsuario(Usuario usuario);

	@EntityGraph(value = "InscripcionWithEstadoInscripcion")
	public Optional<Inscripcion> findWithEstadoInscripcionById(UUID id);

}
