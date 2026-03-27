package org.example.domain.model;

import org.example.domain.model.valueobjects.Dinero;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cita {

    private UUID id;
    private Paciente paciente;
    private LocalDateTime fechaHora;
    private Especialidad especialidad;
    private EstadoCita estadoCita;
    private UUID doctorId;
    // private UUID recursoFisicoId;

    public Cita(UUID id,
                Paciente paciente,
                LocalDateTime fechaHora,
                Especialidad especialidad,
                UUID doctorId,
                UUID recursoFisicoId) {
        this.id = id;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        definirEstadoInicial();
        if (doctorId == null) {
            throw new IllegalArgumentException("El ID del doctor no puede ser nulo.");
        }
        this.doctorId = doctorId;
        //this.recursoFisicoId = recursoFisicoId;

    }

    //Constructor sin ID
    public Cita(Paciente paciente,
                LocalDateTime fechaHora,
                Especialidad especialidad,
                UUID doctorId,
                UUID recursoFisicoId) {
        this.paciente = paciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        definirEstadoInicial();if (doctorId == null) {
            throw new IllegalArgumentException("El ID del doctor no puede ser nulo.");
        }
        this.doctorId = doctorId;
        //this.recursoFisicoId = recursoFisicoId;

    }

    public Cita() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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

    private void definirEstadoInicial() {
        if (this.paciente != null && this.paciente.getTipo() != null) {
            this.estadoCita = (this.paciente.getTipo() == TipoPaciente.NUEVO)
                    ? EstadoCita.PAGADA
                    : EstadoCita.PENDIENTE;
        }
    }

    public UUID getRecursoFisicoId() {
        return null; // Implementar lógica para obtener el ID del recurso físico asociado a la cita
    }
}