package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ciu.grupo1.model.EstadoInscripcion;
import java.util.Optional;

import ciu.grupo1.model.TipoEstadoInscripcion;


public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, Integer> {
	
	public Optional<EstadoInscripcion> findByNombre(TipoEstadoInscripcion nombre);
}
