package ciu.grupo1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import ciu.grupo1.dto.EventoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos", schema="eventos")
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@JdbcTypeCode(org.hibernate.type.SqlTypes.VARCHAR)
	private UUID id;
	private LocalDate fechaEvento;
	private LocalTime horaInicio;
	private String descripcion;
	private String sala;
	private Integer capacidad;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado", referencedColumnName = "id") 
	private EstadoEvento estado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo", referencedColumnName = "id") 
	private TipoEvento tipo;
	
	public EventoDto toDto() {
		EventoDto eventoDto = new EventoDto();
		eventoDto.setCapacidad(capacidad);
		eventoDto.setDescripcion(descripcion);
		eventoDto.setFechaEvento(fechaEvento);
		eventoDto.setHoraInicio(horaInicio);
		eventoDto.setSala(sala);
		return eventoDto;
	}


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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public EstadoEvento getEstado() {
		return estado;
	}

	public void setEstado(EstadoEvento estado) {
		this.estado = estado;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
}
