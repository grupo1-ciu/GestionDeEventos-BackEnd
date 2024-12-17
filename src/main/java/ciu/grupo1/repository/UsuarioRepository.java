package ciu.grupo1.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ciu.grupo1.model.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	public Optional<Usuario> findByEmail(String email); // Use 'email' if that is the correct field for login
	
	public List<Usuario> findAll();
	
	@EntityGraph(value = "UsuarioWithUsuarioRoles")
	public Usuario findWithUsuarioRolesByEmail(String email);
	
	@EntityGraph(value = "UsuarioWithUsuarioRolesRol")
	public Optional<Usuario> findWithUsuariosRolesRolByEmail(String email);
	
	public Usuario findUsuarioByEmail(String email);
	
	Optional<Usuario> findById(UUID id);
}