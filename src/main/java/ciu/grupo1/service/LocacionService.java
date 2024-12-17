package ciu.grupo1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ciu.grupo1.dto.LocacionDto;
import ciu.grupo1.model.Locacion;
import ciu.grupo1.repository.LocacionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocacionService {

    @Autowired
    private LocacionRepository locacionRepository;

    
    public List<LocacionDto> listarTodas() {
        return locacionRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }


    public List<LocacionDto> buscarPorNombre(String nombre) {
        return locacionRepository.findByNombre(nombre)
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    public Optional<LocacionDto> buscarPorId(UUID id){
    	return locacionRepository.findById(id)
    			.map(this::convertirADto);
    }

    
    public List<LocacionDto> filtrarPorCapacidad(Integer capacidadMaxima) {
        return locacionRepository.findByCapacidadMaxima(capacidadMaxima)
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

  
    public LocacionDto guardar(LocacionDto locacionDto) {
        Locacion locacion = convertirAEntidad(locacionDto);
        return convertirADto(locacionRepository.save(locacion));
    }

   
    public void eliminar(UUID id) {
        locacionRepository.deleteById(id);
    }

    
    private LocacionDto convertirADto(Locacion locacion) {
        LocacionDto dto = new LocacionDto();
        dto.setId(locacion.getId());
        dto.setNombre(locacion.getNombre());
        dto.setCapacidadMaxima(locacion.getCapacidadMaxima());
        dto.setDireccion(locacion.getDireccion());
        dto.setTieneEstacionamiento(locacion.isTieneEstacionamiento());
        return dto;
    }


    private Locacion convertirAEntidad(LocacionDto dto) {
        Locacion locacion = new Locacion();
        locacion.setId(dto.getId());
        locacion.setNombre(dto.getNombre());
        locacion.setCapacidadMaxima(dto.getCapacidadMaxima());
        locacion.setDireccion(dto.getDireccion());
        locacion.setTieneEstacionamiento(dto.isTieneEstacionamiento());
        return locacion;
    }
}