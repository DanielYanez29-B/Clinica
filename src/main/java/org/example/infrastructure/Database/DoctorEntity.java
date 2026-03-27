package org.example.infrastructure.Database;

import jakarta.persistence.*;
import org.example.domain.model.Especialidad;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "doctores")
public class DoctorEntity {

    @Id
    private UUID id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_disponibilidad", joinColumns = @JoinColumn(name = "doctor_id"))
    private List<DisponibilidadEmbeddable> disponibilidades;

    public DoctorEntity() {}

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<DisponibilidadEmbeddable> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<DisponibilidadEmbeddable> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
