package ciu.grupo1.model;

import java.util.UUID;

import jakarta.persistence.*;


	@Entity
	@Table(name = "locaciones")
	public class Locacion {
	    
		@Id
	    @GeneratedValue(generator = "UUID")
	    private UUID id;
	    private String nombre;
	    private Integer capacidadMaxima;
	    private String direccion;
	    private boolean tieneEstacionamiento;

	   
	    public UUID getId() {
	        return id;
	    }

	    public void setId(UUID id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public int getCapacidadMaxima() {
	        return capacidadMaxima;
	    }

	    public void setCapacidadMaxima(int capacidadMaxima) {
	        this.capacidadMaxima = capacidadMaxima;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public boolean isTieneEstacionamiento() {
	        return tieneEstacionamiento;
	    }

	    public void setTieneEstacionamiento(boolean tieneEstacionamiento) {
	        this.tieneEstacionamiento = tieneEstacionamiento;
	    }
}

