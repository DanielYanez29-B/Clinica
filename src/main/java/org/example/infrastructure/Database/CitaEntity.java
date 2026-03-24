package org.example.infrastructure.Database;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas_medicas")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePaciente;

    private LocalDateTime fechaHora;

    public CitaEntity() {
    }

    public CitaEntity(String nombrePaciente, LocalDateTime fechaHora) {
        this.nombrePaciente = nombrePaciente;
        this.fechaHora = fechaHora;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }


}
