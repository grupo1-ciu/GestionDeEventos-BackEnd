package ciu.grupo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Eventos")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
}
