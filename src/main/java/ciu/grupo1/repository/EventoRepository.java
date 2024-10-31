package ciu.grupo1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
	
}