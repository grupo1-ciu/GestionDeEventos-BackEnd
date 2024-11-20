package ciu.grupo1.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID>{
	
	public List<Evento> findAll();
	
	@Query("""
			SELECT e FROM Evento e 
			WHERE e.estado.nombreEstadoEvento IN('DISPONIBLE', 'EN_CURSO', 'POSPUESTO')
			""")
	public List<Evento> findAllDisponibles();

}
