package ciu.grupo1.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos", schema="eventos")
public class Evento {
	
	@Id
	private UUID id;
	private LocalDate fechaEvento;
	private LocalTime horaInicio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado")
	private EstadoEvento estado;
	
	@OneToMany(mappedBy = "evento")
	private HashSet<Inscripcion> inscripciones;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sala")
	private Sala sala;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo")
	private TipoEvento tipo;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public EstadoEvento getEstado() {
		return estado;
	}

	public void setEstado(EstadoEvento estado) {
		this.estado = estado;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	
}
