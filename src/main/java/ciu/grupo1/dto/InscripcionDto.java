package ciu.grupo1.dto;

public class InscripcionDto {
	
	private UsuarioAdminDto usuario;
	private EventoDto evento;
	private EstadoInscripcionDto estadoInscripcion;
	
	public UsuarioAdminDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioAdminDto usuario) {
		this.usuario = usuario;
	}
	public EventoDto getEvento() {
		return evento;
	}
	public void setEvento(EventoDto evento) {
		this.evento = evento;
	}
	public EstadoInscripcionDto getEstadoInscripcion() {
		return estadoInscripcion;
	}
	public void setEstadoInscripcion(EstadoInscripcionDto estadoInscripcion) {
		this.estadoInscripcion = estadoInscripcion;
	}
}
