package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.dto.LocacionDto;
import ciu.grupo1.model.Locacion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocacionRepository extends JpaRepository<Locacion, UUID> {
    List<Locacion> findByNombre(String nombre);
    List<Locacion> findByCapacidadMaxima(Integer capacidadMaxima); /*<--Estaa bien asi o twndria que mostrar tamb los q tienen mas? =/ */
}
