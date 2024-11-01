package ciu.grupo1.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ciu.grupo1.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, UUID>{

}
