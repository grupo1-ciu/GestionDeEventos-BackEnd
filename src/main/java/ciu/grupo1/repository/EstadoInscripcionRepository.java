package ciu.grupo1.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ciu.grupo1.model.EstadoInscripcion;


public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, UUID>{

	 Optional<EstadoInscripcion> findByNombre(String nombreEstado);

}

