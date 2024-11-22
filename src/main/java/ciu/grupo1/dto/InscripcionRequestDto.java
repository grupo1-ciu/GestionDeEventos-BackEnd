package ciu.grupo1.dto;

import java.util.UUID;

public class InscripcionRequestDto {
    private UUID idEvento;
    private String emailUsuario;

    // Getters y Setters
    public UUID getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(UUID idEvento) {
        this.idEvento = idEvento;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}

