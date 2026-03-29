package org.example.application.dto.response;

import org.example.domain.model.Especialidad;
import org.example.domain.model.EstadoCita;

import java.time.LocalDateTime;
import java.util.UUID;

public class CitaResponse {
    private UUID id;
    private UUID pacienteId;
    private LocalDateTime fechaHora;
    private LocalDateTime fechaHoraFin;
    private Especialidad especialidad;
    private EstadoCita estadoCita;
    private UUID doctorId;
    private UUID recursoFisicoId;

    public CitaResponse() {}

    public CitaResponse(UUID id, UUID pacienteId,
                        LocalDateTime fechaHora,
                        LocalDateTime fechaHoraFin,
                        Especialidad especialidad,
                        EstadoCita estadoCita,
                        UUID doctorId,
                        UUID recursoFisicoId) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.especialidad = especialidad;
        this.estadoCita = estadoCita;
        this.doctorId = doctorId;
        this.recursoFisicoId = recursoFisicoId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPacienteId() { return pacienteId; }
    public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public EstadoCita getEstadoCita() { return estadoCita; }
    public void setEstadoCita(EstadoCita estadoCita) { this.estadoCita = estadoCita; }

    public UUID getDoctorId() { return doctorId; }
    public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }

    public UUID getRecursoFisicoId() { return recursoFisicoId; }
    public void setRecursoFisicoId(UUID recursoFisicoId) { this.recursoFisicoId = recursoFisicoId; }
}
