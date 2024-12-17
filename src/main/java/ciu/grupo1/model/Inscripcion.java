package ciu.grupo1.model;


import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import ciu.grupo1.dto.InscripcionDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;

@NamedEntityGraph(name="InscripcionesWithEventoAndUsuarioAndEstadoInscripcion",
	attributeNodes = {
		@NamedAttributeNode("usuario"),
		@NamedAttributeNode("evento"),
		@NamedAttributeNode("estadoInscripcion")	
	}
)

@NamedEntityGraph(name="InscripcionWithEstadoInscripcion", 
	attributeNodes = @NamedAttributeNode("estadoInscripcion")
)

@Entity
@Table(name = "inscripciones", schema = "eventos")
public class Inscripcion {

	
	@Id
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento")
	private Evento evento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado_inscripcion")
	private EstadoInscripcion estadoInscripcion;
	
	public InscripcionDto toDto() {
		InscripcionDto inscripcionDto = new InscripcionDto();
		inscripcionDto.setId(id.toString());
		inscripcionDto.setEvento(this.evento.toDto());
		inscripcionDto.setEstadoInscripcion(this.estadoInscripcion.toDto());
		inscripcionDto.setUsuario(this.usuario.toAdminDto());
		return inscripcionDto;
	}

   
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

}
