package ciu.grupo1.repository;

import ciu.grupo1.model.EstadoInscripcion;
import ciu.grupo1.model.TipoEstadoInscripcion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, Integer> {
	
	Optional<EstadoInscripcion>  getByNombre(TipoEstadoInscripcion nombreEstadoInscripcion);
 
}
