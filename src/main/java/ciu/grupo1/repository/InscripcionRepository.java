package ciu.grupo1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ciu.grupo1.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, UUID> {
    
   
    boolean existsByUsuarioIdAndEventoId(UUID usuarioId, UUID eventoId);
    int countByEventoId(UUID eventoId);
}
