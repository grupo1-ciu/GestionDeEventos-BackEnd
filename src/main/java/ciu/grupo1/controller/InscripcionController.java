package ciu.grupo1.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ciu.grupo1.dto.InscripcionRequestDto;
import ciu.grupo1.model.Evento;
import ciu.grupo1.model.Inscripcion;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.service.EstadoInscripcionService;
import ciu.grupo1.service.EventoService;
import ciu.grupo1.service.InscripcionService;
import ciu.grupo1.service.UsuarioService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EstadoInscripcionService estadoInscripcionService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> inscribirUsuario(@RequestBody InscripcionRequestDto inscripcionRequest) {

        UUID idEvento = inscripcionRequest.getIdEvento();
        String emailUsuario = inscripcionRequest.getEmailUsuario();

        Usuario usuario = usuarioService.getByEmail(emailUsuario).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (inscripcionService.estaInscrito(usuario.getId(), idEvento)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya está inscrito en el evento");
        }

        Evento evento = eventoService.obtenerEventoPorId(idEvento);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }

        int cuposDisponibles = (int) (evento.getCapacidad() - inscripcionService.contarInscripciones(idEvento));
        if (cuposDisponibles <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay cupos disponibles para el evento");
        }

        if (!evento.getEstado().getNombreEstadoEvento().name().equalsIgnoreCase("DISPONIBLE")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El evento no está abierto para inscripciones");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setEvento(evento);
        inscripcion.setEstadoInscripcion(this.estadoInscripcionService.setEstadoPendiente());
        inscripcionService.guardarInscripcion(inscripcion);

        return ResponseEntity.ok("Inscripción realizada con éxito");
    }

}
