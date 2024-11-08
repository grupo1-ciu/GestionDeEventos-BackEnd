package ciu.grupo1.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	@EntityGraph(value = "UsuarioFindWichId")
	public Optional<Usuario> findById(UUID idUsuario);
	
	@Query("FROM Usuario u " + 
			"LEFT JOIN FETCH u.inscripciones i " +
			"WHERE u.email=:email")
	@EntityGraph(value="UsuarioWithInscripcionesAndUsuarioRol")
	public Usuario findWithInscripcionesByEmail (String email);
	
//	@EntityGraph(value = "UsuarioWithUsuarioRolesRolAndInscripciones") 
//	public Optional<Usuario> findWithUsuarioRolesRolAndInscripcionesByEmail(String email);
	
	
}