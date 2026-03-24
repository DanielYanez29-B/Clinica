package org.example.domain.model;

import java.time.LocalDateTime;

public class Cita {

    private Long id;
    private String nombrePaciente;
    private LocalDateTime fechaHora;
    private Especialidad especialidad;

    public Cita(long id, String nombrePaciente, LocalDateTime fechaHora, Especialidad especialidad) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;


    }

    //Constructor sin ID
    public Cita(String nombrePaciente, LocalDateTime fechaHora) {
        this.nombrePaciente = nombrePaciente;
        this.fechaHora = fechaHora;
    }

    public Cita(){}

    public Long getId() {
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
