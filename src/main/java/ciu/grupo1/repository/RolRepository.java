package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.Rol;
import ciu.grupo1.model.TipoRol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	public Rol findByNombre(TipoRol nombreRol);
}
