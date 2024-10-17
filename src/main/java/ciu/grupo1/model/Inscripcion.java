package ciu.grupo1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inscripcion")
public class Inscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UserInfo usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private EstadoInscripcion estadoIncripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUsuario() {
		return usuario;
	}

	public void setUsuario(UserInfo usuario) {
		this.usuario = usuario;
	}
}
