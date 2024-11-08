package ciu.grupo1.model;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedEntityGraph(name = "UsuarioWithUsuarioRoles",
	attributeNodes = @NamedAttributeNode("usuarioRoles")
)
@NamedEntityGraph(name = "UsuarioWithUsuarioRolesRol",
	attributeNodes = @NamedAttributeNode(value = "usuarioRoles", subgraph = "usuarioRoles-subgraph"),
	subgraphs = {
		@NamedSubgraph(
			name = "usuarioRoles-subgraph",
			attributeNodes = {
				@NamedAttributeNode("rol")
			}
		)
	}
)

@Table(name = "usuarios", schema = "eventos")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "usuario")
	private Set<UsuarioRol> usuarioRoles;
	
//	@OneToMany(mappedBy = "usuario")
//	private Set<Inscripcion> inscripciones;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

//	public Set<Inscripcion> getInscripciones() {
//		return inscripciones;
//	}
//
//	public void setInscripciones(Set<Inscripcion> inscripciones) {
//		this.inscripciones = inscripciones;
//	}
	
	
}
