package ciu.grupo1.repository;

import ciu.grupo1.model.EstadoInscripcion;
import ciu.grupo1.model.TipoEstadoInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, UUID> {
 
    Optional<EstadoInscripcion> findByNombreEstadoInscripcion(TipoEstadoInscripcion nombreEstadoInscripcion);
}
