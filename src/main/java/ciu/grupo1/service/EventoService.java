package ciu.grupo1.service;


import java.util.List;

import ciu.grupo1.model.Evento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ciu.grupo1.dto.EventoDto;
import ciu.grupo1.model.CategoriaEvento;
import ciu.grupo1.model.EstadoEvento;
import ciu.grupo1.model.Evento;
import ciu.grupo1.model.FaseEvento;
import ciu.grupo1.model.TipoEvento;
import ciu.grupo1.repository.EstadoEventoRepository;
import ciu.grupo1.repository.EventoRepository;
import ciu.grupo1.repository.TipoEventoRepository;

@Service
public class EventoService {
    
  @Autowired
	private EstadoEventoRepository estadoEventoRepository;
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private TipoEventoRepository tipoEventoRepository;
	
	@Transactional(readOnly = true)
	public List<Evento> getAllEventos(){
		return this.eventoRepository.findAll();
	}
	
	@Transactional
	public Evento addEvent(EventoDto eventoDto) {
		Evento evento = eventoDto.toModel();
		EstadoEvento ee = estadoEventoRepository.findByNombreEstadoEvento(FaseEvento.DISPONIBLE);
		TipoEvento te = tipoEventoRepository.findByNombre(CategoriaEvento.valueOf(eventoDto.getTipoEvento().toUpperCase()));
		evento.setId(UUID.randomUUID());
		evento.setFechaEvento(eventoDto.getFechaEvento());
		evento.setHoraInicio(eventoDto.getHoraInicio());
		evento.setDescripcion(eventoDto.getDescripcion());
		evento.setCapacidad(eventoDto.getCapacidad());
		evento.setSala(eventoDto.getSala());
		evento.setTipo(te);
		evento.setEstado(ee);
		eventoRepository.save(evento);
		
		return evento;
	}
	
	@Transactional(readOnly = true)
	public List<Evento> getAllEventosDisponibles() {
		return this.eventoRepository.findAllDisponibles();
	}
}
