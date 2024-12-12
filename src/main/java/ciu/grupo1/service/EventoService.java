package ciu.grupo1.service;

import java.util.List;
import java.util.Optional;

import ciu.grupo1.model.Evento;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ciu.grupo1.dto.EventoDto;
import ciu.grupo1.model.CategoriaEvento;
import ciu.grupo1.model.EstadoEvento;
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
	
    public Evento obtenerEventoPorId(UUID idEvento) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);
        return evento.orElse(null);
    }
	
	@Transactional
	public Evento addEvent(EventoDto eventoDto) {
		EstadoEvento estadoPredeterminado = estadoEventoRepository.findByNombreEstadoEvento(FaseEvento.DISPONIBLE);
		 if (estadoPredeterminado == null) {
	            estadoPredeterminado = new EstadoEvento(FaseEvento.DISPONIBLE);
	            estadoEventoRepository.save(estadoPredeterminado);
	        }
		TipoEvento te = tipoEventoRepository.findByNombre(CategoriaEvento.valueOf(eventoDto.getTipoEvento().getNombre().toUpperCase()));
		Evento evento = eventoDto.toModel(estadoPredeterminado, te);
		evento.setId(UUID.randomUUID());
		evento.setFechaEvento(eventoDto.getFechaEvento());
		evento.setHoraInicio(eventoDto.getHoraInicio());
		evento.setDescripcion(eventoDto.getDescripcion());
		evento.setCapacidad(eventoDto.getCapacidad());
		evento.setSala(eventoDto.getSala());
		evento.setTipo(te);
		evento.setEstado(estadoPredeterminado);
		eventoRepository.save(evento);
		
		return evento;
	}
	
	@Transactional
	public Evento editEvent(UUID idEvento, EventoDto eventoDto) {
	    Evento evento = eventoRepository.findById(idEvento)
	        .orElseThrow(() -> new IllegalArgumentException("Evento con ID " + idEvento + " no encontrado."));

	    evento.setFechaEvento(eventoDto.getFechaEvento());
	    evento.setHoraInicio(eventoDto.getHoraInicio());
	    evento.setDescripcion(eventoDto.getDescripcion());
	    evento.setCapacidad(eventoDto.getCapacidad());
	    evento.setSala(eventoDto.getSala());
	    
	    if (eventoDto.getTipoEvento() != null) {
	        TipoEvento nuevoTipo = tipoEventoRepository.findByNombre(
	            CategoriaEvento.valueOf(eventoDto.getTipoEvento().getNombre().toUpperCase())
	        );
	        if (nuevoTipo == null) {
	            throw new IllegalArgumentException("Tipo de evento no válido: " + eventoDto.getTipoEvento().getNombre());
	        }
	        evento.setTipo(nuevoTipo);
	    }

	    if (eventoDto.getEstadoEvento() != null) {
	        String nombreEstado = eventoDto.getEstadoEvento().getNombreEstado();
	        if (nombreEstado == null || nombreEstado.isEmpty()) {
	            throw new IllegalArgumentException("El nombre del estado de evento no puede ser nulo o vacío.");
	        }
	        
	        EstadoEvento nuevoEstado = estadoEventoRepository.findByNombreEstadoEvento(
	            FaseEvento.valueOf(nombreEstado.toUpperCase())
	        );
	        if (nuevoEstado == null) {
	            throw new IllegalArgumentException("Estado de evento no válido: " + nombreEstado);
	        }
	        evento.setEstado(nuevoEstado);
	    }

//	    if (eventoDto.getEstadoEvento() != null) {
//	        EstadoEvento nuevoEstado = estadoEventoRepository.findByNombreEstadoEvento(
//	            FaseEvento.valueOf(eventoDto.getEstadoEvento().getNombreEstado().toUpperCase())
//	        );
//	        if (nuevoEstado == null) {
//	            throw new IllegalArgumentException("Estado de evento no válido: " + eventoDto.getEstadoEvento().getNombreEstado());
//	        }
//	        evento.setEstado(nuevoEstado);
//	    }

	    eventoRepository.save(evento);

	    return evento;
	}
	
//	@Transactional
//	public void deleteEvent(UUID idEvento) {
//	    if (!eventoRepository.existsById(idEvento)) {
//	        throw new IllegalArgumentException("Evento con ID " + idEvento + " no encontrado.");
//	    }
//	    eventoRepository.deleteById(idEvento);
//	}
	
	@Transactional
	public void deleteEvent(UUID idEvento) {
	    Evento evento = eventoRepository.findById(idEvento)
	        .orElseThrow(() -> new IllegalArgumentException("Evento con ID " + idEvento + " no encontrado."));
	    evento.setDeleted(true);
	    eventoRepository.save(evento);
	}

	
	@Transactional(readOnly = true)
	public List<Evento> getAllEventosDisponibles() {
		return this.eventoRepository.findAllDisponibles();
	}
	
	@Transactional(readOnly = true)
	public EventoDto getEvento(String idEvento) {
		UUID uuidEvento = UUID.fromString(idEvento);
		Evento evento = this.eventoRepository.findById(uuidEvento).orElse(null);
		
		return evento.toDto();
	}

}
