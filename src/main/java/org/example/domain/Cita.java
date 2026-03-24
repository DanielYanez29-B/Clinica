package org.example.domain;

import java.time.LocalDateTime;

public class Cita {

    private long id;
    private String nombrePaciente;
    private LocalDateTime fechaHora;

    public Cita(long id, String nombrePaciente, LocalDateTime fechaHora) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.fechaHora = fechaHora;
    }

    //Constructor sin ID
    public Cita(String nombrePaciente, LocalDateTime fechaHora) {
        this.nombrePaciente = nombrePaciente;
        this.fechaHora = fechaHora;
    }

    public Cita(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
