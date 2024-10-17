package ciu.grupo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EstadoInscripcion")
public class EstadoInscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre_estado_inscripcion;
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private Inscripcion inscripciones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_estado_inscripcion() {
		return nombre_estado_inscripcion;
	}

	public void setNombre_estado_inscripcion(String nombre_estado_inscripcion) {
		this.nombre_estado_inscripcion = nombre_estado_inscripcion;
	}

	public Inscripcion getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Inscripcion inscripciones) {
		this.inscripciones = inscripciones;
	}
}
