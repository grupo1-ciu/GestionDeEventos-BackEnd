package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.EstadoEvento;
import ciu.grupo1.model.FaseEvento;

@Repository
public interface EstadoEventoRepository extends JpaRepository<EstadoEvento, Integer> {
	
	public EstadoEvento findByNombreEstadoEvento(FaseEvento faseEvento);
}
