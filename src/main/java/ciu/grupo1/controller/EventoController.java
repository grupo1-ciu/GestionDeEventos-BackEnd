package ciu.grupo1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{idEvento}")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<EventoDto> buscarEventoPorId(@PathVariable String idEvento){
		EventoDto eventoDto = this.eventoService.getEvento(idEvento);
		return ResponseEntity.ok(eventoDto);
	}
	
	@PostMapping("/crearEvento")
	public EventoDto addNewEvent(@RequestBody EventoDto eventoDto) {
	    Evento evento = eventoService.addEvent(eventoDto);
	    return evento.toDto();
	}
	
	@PutMapping("/{idEvento}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<EventoDto> editEvent(@PathVariable UUID idEvento, @RequestBody EventoDto eventoDto) {
	    Evento eventoActualizado = eventoService.editEvent(idEvento, eventoDto);
	    if (eventoActualizado == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(eventoActualizado.toDto());
	}
	
	@DeleteMapping("/{idEvento}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEvent(@PathVariable UUID idEvento) {
	    try {
	        eventoService.deleteEvent(idEvento);
	        return ResponseEntity.ok("Evento eliminado correctamente");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el evento");
	    }
	}

}
