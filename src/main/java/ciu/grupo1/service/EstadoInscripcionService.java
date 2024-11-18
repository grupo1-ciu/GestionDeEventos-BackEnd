package ciu.grupo1.service;

import ciu.grupo1.model.EstadoInscripcion;
import ciu.grupo1.model.TipoEstadoInscripcion;
import ciu.grupo1.repository.EstadoInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoInscripcionService {

    @Autowired
    private EstadoInscripcionRepository estadoInscripcionRepository;

    public EstadoInscripcion save(EstadoInscripcion estadoInscripcion) {
        return estadoInscripcionRepository.save(estadoInscripcion);
    }

    public EstadoInscripcion findById(int id) {
        return estadoInscripcionRepository.findById(id).orElse(null);
    }
    
	public EstadoInscripcion setEstadoPendiente() {
		return this.estadoInscripcionRepository.getByNombre(TipoEstadoInscripcion.PENDIENTE)
	                                           .orElseThrow(() -> new IllegalStateException("Estado PENDIENTE no encontrado"));
	}
}
