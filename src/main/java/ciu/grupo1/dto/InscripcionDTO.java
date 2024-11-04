package ciu.grupo1.dto;
import java.util.UUID;

public class InscripcionDTO {

	    private UUID id;
	    private UUID idUsuario;
	    private UUID idEvento;
	    private String estadoInscripcion;

	    
	    public InscripcionDTO(UUID id, UUID idUsuario, UUID idEvento, String estadoInscripcion) {
	        this.id = id;
	        this.idUsuario = idUsuario;
	        this.idEvento = idEvento;
	        this.estadoInscripcion = estadoInscripcion;
	    }

	    public UUID getId() {
	        return id;
	    }

	    public void setId(UUID id) {
	        this.id = id;
	    }

	    public UUID getIdUsuario() {
	        return idUsuario;
	    }

	    public void setIdUsuario(UUID idUsuario) {
	        this.idUsuario = idUsuario;
	    }

	    public UUID getIdEvento() {
	        return idEvento;
	    }

	    public void setIdEvento(UUID idEvento) {
	        this.idEvento = idEvento;
	    }

	    public String getEstadoInscripcion() {
	        return estadoInscripcion;
	    }

	    public void setEstadoInscripcion(String estadoInscripcion) {
	        this.estadoInscripcion = estadoInscripcion;
	    }
}

