package org.example.infrastructure.Database;

import jakarta.persistence.*;
import org.example.domain.model.Especialidad;
import org.example.domain.model.EstadoCita;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "citas_medicas")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Enumerated(EnumType.STRING)
    private EstadoCita estadoCita;

    public CitaEntity() {
    }

    public CitaEntity(UUID id, PacienteEntity paciente, LocalDateTime fechaHora, Especialidad especialidad, EstadoCita estadoCita) {
        this.id = id;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        this.estadoCita = estadoCita;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public PacienteEntity paciente() { return paciente; }
    public void setPaciente(PacienteEntity paciente) { this.paciente = paciente; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public EstadoCita getEstadoCita() { return estadoCita; }
    public void setEstadoCita(EstadoCita estadoCita) { this.estadoCita = estadoCita; }

}
