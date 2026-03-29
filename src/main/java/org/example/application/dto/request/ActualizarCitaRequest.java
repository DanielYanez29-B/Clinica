package org.example.application.dto.request;

import org.example.domain.model.Especialidad;
import org.example.domain.model.EstadoCita;

import java.time.LocalDateTime;
import java.util.UUID;

public class ActualizarCitaRequest {
    private LocalDateTime fechaHora;
    private Especialidad especialidad;
    private EstadoCita estadoCita;
    private UUID doctorId;
    private UUID recursoFisicoId;

    public ActualizarCitaRequest() {}

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public EstadoCita getEstadoCita() { return estadoCita; }
    public void setEstadoCita(EstadoCita estadoCita) { this.estadoCita = estadoCita; }

    public UUID getDoctorId() { return doctorId; }
    public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }

    public UUID getRecursoFisicoId() { return recursoFisicoId; }
    public void setRecursoFisicoId(UUID recursoFisicoId) { this.recursoFisicoId = recursoFisicoId; }
}
