package org.example.domain.model;


import org.example.domain.model.valueobjects.Dinero;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Cita {

    private UUID id;
    private UUID pacienteId;
    private LocalDateTime fechaHora;
    private Especialidad especialidad;
    private EstadoCita estadoCita;
    private UUID doctorId;
    // private UUID recursoFisicoId;

    // Constructor con ID (para recuperar de BD)
    public Cita(UUID id,
                UUID pacienteId,
                LocalDateTime fechaHora,
                Especialidad especialidad,
                UUID doctorId,
                UUID recursoFisicoId,
                EstadoCita estadoCita) {
        this.id = Objects.requireNonNull(id, "El ID de la cita no puede ser nulo.");
        this.pacienteId = Objects.requireNonNull(pacienteId, "El ID del paciente no puede ser nulo.");
        this.fechaHora = Objects.requireNonNull(fechaHora, "La fecha/hora no puede ser nula.");
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad no puede ser nula.");
        this.doctorId = Objects.requireNonNull(doctorId, "El ID del doctor no puede ser nulo.");
        this.estadoCita = Objects.requireNonNull(estadoCita, "El estado de la cita no puede ser nulo.");
    }
    public Cita() {
        // Constructor vacío para frameworks que lo requieran (e.g., JPA, MapStruct)
    }
    // Constructor sin ID (para crear Cita nueva)
    public Cita(UUID pacienteId,
                LocalDateTime fechaHora,
                Especialidad especialidad,
                UUID doctorId,
                UUID recursoFisicoId,
                EstadoCita estadoCita) {
        this.pacienteId = Objects.requireNonNull(pacienteId, "El ID del paciente no puede ser nulo.");
        this.fechaHora = Objects.requireNonNull(fechaHora, "La fecha/hora no puede ser nula.");
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad no puede ser nula.");
        this.doctorId = Objects.requireNonNull(doctorId, "El ID del doctor no puede ser nulo.");
        this.estadoCita = Objects.requireNonNull(estadoCita, "El estado de la cita no puede ser nulo.");
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
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

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getFechaHoraFin() {
        if (this.fechaHora != null && this.especialidad != null) {
            return this.fechaHora.plusMinutes(this.especialidad.getDuracionMinutos());
        }
        return null;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public UUID getDoctorId() {
        return doctorId;
    }


    public Dinero calcularTotal() {
        return this.especialidad.getCostoBase();
    }


    public UUID getRecursoFisicoId() {
        return null; // Implementar lógica para obtener el ID del recurso físico asociado a la cita
    }

    public void setDoctorId(UUID doctorId) {this.doctorId = doctorId;}

    public void setRecursoFisicoId(UUID recursoFisicoId) {
        // Implementar lógica para establecer el ID del recurso físico asociado a la cita
    }
}