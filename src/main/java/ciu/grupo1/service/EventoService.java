package ciu.grupo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ciu.grupo1.model.Evento;
import ciu.grupo1.repository.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	public List<Evento> getAllEventos(){
		return this.eventoRepository.findAll();
	}
}
