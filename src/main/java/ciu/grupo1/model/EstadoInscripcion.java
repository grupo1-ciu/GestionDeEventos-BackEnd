package ciu.grupo1.model;

import ciu.grupo1.dto.EstadoInscripcionDto;
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
	private int id;
	
	@Enumerated(EnumType.STRING)
	private TipoEstadoInscripcion nombre;
	
	public EstadoInscripcionDto toDto() {
		EstadoInscripcionDto estadoInscripcionDto = new EstadoInscripcionDto();
		estadoInscripcionDto.setEstado(this.nombre.toString());
		return estadoInscripcionDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoEstadoInscripcion getNombre() {
		return nombre;
	}

	public void setNombre(TipoEstadoInscripcion nombreEstadoInscripcion) {
		this.nombre = nombreEstadoInscripcion;
	}
}
