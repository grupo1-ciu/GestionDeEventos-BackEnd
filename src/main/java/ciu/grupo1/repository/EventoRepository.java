package ciu.grupo1.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID>{
	
	@EntityGraph(value = "EventoWithEstadoAndTipoEvento")
	@Query("SELECT e FROM Evento e WHERE e.deleted = false")
	public List<Evento> findAll();
	
	@EntityGraph(value = "EventoWithEstadoAndTipoEvento")
	@Query("""
            SELECT e FROM Evento e 
            WHERE e.estado.nombreEstadoEvento IN ('DISPONIBLE', 'EN_CURSO', 'POSPUESTO')
              AND e.deleted = false
            """)
	public List<Evento> findAllDisponibles();
	
	@EntityGraph(value = "EventoWithEstadoAndTipoEvento")
	 @Query("SELECT e FROM Evento e WHERE e.id = :idEvento AND e.deleted = false")
	public Optional<Evento> findById(UUID idEvento);

}
