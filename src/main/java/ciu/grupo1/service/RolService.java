package ciu.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ciu.grupo1.model.Rol;
import ciu.grupo1.model.TipoRol;
import ciu.grupo1.repository.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository rolRepository;
	
	public Rol findByName(TipoRol nombreRol) {
		return this.rolRepository.findByNombre(nombreRol);
	}
}
