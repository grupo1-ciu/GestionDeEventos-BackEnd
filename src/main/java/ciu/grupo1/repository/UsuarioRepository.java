package ciu.grupo1.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ciu.grupo1.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Optional<Usuario> findByEmail(String email); // Use 'email' if that is the correct field for login
	
}