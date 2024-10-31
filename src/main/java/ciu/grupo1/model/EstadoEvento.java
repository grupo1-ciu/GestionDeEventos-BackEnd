package ciu.grupo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estados_eventos", schema="eventos")
public class EstadoEvento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private FaseEvento nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FaseEvento getNombreEstadoEvento() {
		return nombre;
	}

	public void setNombreEstadoEvento(FaseEvento nombreEstadoEvento) {
		this.nombre = nombreEstadoEvento;
	}
}