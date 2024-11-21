package ciu.grupo1.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import ciu.grupo1.model.Evento;

public class EventoDto {
	
	private LocalDate fechaEvento;
	private LocalTime horaInicio;
	private String descripcion;
	private String sala;
	private Integer capacidad;
	private String tipoEvento;
	
	public Evento toModel() {
		Evento evento = new Evento();
		
		evento.setFechaEvento(fechaEvento);
		evento.setHoraInicio(horaInicio);
		evento.setDescripcion(descripcion);
		evento.setSala(sala);
		evento.setCapacidad(capacidad);
		
		return evento;
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

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
	
}
