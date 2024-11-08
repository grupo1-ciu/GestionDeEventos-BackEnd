package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ciu.grupo1.model.EstadoInscripcion;
import ciu.grupo1.model.TipoEstadoInscripcion;


import java.util.Optional;
import java.util.UUID;

public interface EstadoInscripcionRepository extends JpaRepository<EstadoInscripcion, Integer> {
 
    Optional<EstadoInscripcion> findByNombreEstadoInscripcion(TipoEstadoInscripcion nombreEstadoInscripcion);
}
