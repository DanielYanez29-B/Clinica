package org.example.application.dto.request;

import org.example.domain.model.Especialidad;

import java.time.LocalDateTime;
import java.util.UUID;

public class CrearCitaRequest {
    private UUID pacienteId;
    private LocalDateTime fechaHora;
    private Especialidad especialidad;
    private UUID doctorId;
    private UUID recursoFisicoId;

    public CrearCitaRequest() {}

    public CrearCitaRequest(UUID pacienteId,
                            LocalDateTime fechaHora,
                            Especialidad especialidad,
                            UUID doctorId,
                            UUID recursoFisicoId) {
        this.pacienteId = pacienteId;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        this.doctorId = doctorId;
        this.recursoFisicoId = recursoFisicoId;
    }

    public UUID getPacienteId() { return pacienteId; }
    public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public UUID getDoctorId() { return doctorId; }
    public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }

    public UUID getRecursoFisicoId() { return recursoFisicoId; }
    public void setRecursoFisicoId(UUID recursoFisicoId) { this.recursoFisicoId = recursoFisicoId; }
}
