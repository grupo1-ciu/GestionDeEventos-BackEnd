package ciu.grupo1.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estados_inscripciones", schema="eventos")
public class EstadoInscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	private TipoEstadoInscripcion nombreEstadoInscripcion;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public TipoEstadoInscripcion getNombreEstadoInscripcion() {
		return nombreEstadoInscripcion;
	}

	public void setNombreEstadoInscripcion(TipoEstadoInscripcion nombreEstadoInscripcion) {
		this.nombreEstadoInscripcion = nombreEstadoInscripcion;
	}
}
