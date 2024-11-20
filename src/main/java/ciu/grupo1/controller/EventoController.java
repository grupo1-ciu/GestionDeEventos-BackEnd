package ciu.grupo1.controller;


import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ciu.grupo1.dto.EventoDto;
import ciu.grupo1.model.Evento;
import ciu.grupo1.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
	
	@Autowired
	private EventoService eventoService;
	

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<EventoDto> buscarEventosDisponibles(){
		List<Evento> eventos = this.eventoService.getAllEventosDisponibles();
		List<EventoDto> eventosDto = eventos.stream().map(evento -> evento.toDto()).toList();
		return eventosDto;
	}

	@PostMapping()
	public EventoDto addNewEvent(@RequestBody EventoDto eventoDto) {
		Evento evento = eventoService.addEvent(eventoDto);
		EventoDto eventoDto1 = new EventoDto();
		//eventoDto1.setId(evento.getId());
		return eventoDto1;
	}
	

}
