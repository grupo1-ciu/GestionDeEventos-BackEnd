package ciu.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.CategoriaEvento;
import ciu.grupo1.model.TipoEvento;

@Repository
public interface TipoEventoRepository extends JpaRepository<TipoEvento, Integer> {
	
	public TipoEvento findByNombre(CategoriaEvento categoriaEvento);
}
