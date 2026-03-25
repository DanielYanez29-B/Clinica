package org.example.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cita {

    private UUID id;
    private Paciente paciente;
    private LocalDateTime fechaHora;
    private Especialidad especialidad;

    public Cita(UUID id, Paciente paciente, LocalDateTime fechaHora, Especialidad especialidad) {
        this.id = id;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;


    }

    //Constructor sin ID
    public Cita(Paciente paciente, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Cita(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public LocalDateTime getFechaHoraFin() {
        if (this.fechaHora != null && this.especialidad != null) {
            return this.fechaHora.plusMinutes(this.especialidad.getDuracionMinutos());
        }
        return null;
    }
}
