package org.example.infrastructure.Database;


import jakarta.persistence.*;
import org.example.domain.model.Paciente;
import org.example.domain.model.TipoPaciente;

import java.util.UUID;

@Entity
@Table(name = "pacientes")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoPaciente tipo;

    public PacienteEntity() {
    }

    public PacienteEntity(UUID id, String nombre, TipoPaciente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public UUID getId() { return id; }
    //public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoPaciente getTipo() { return tipo; }
    public void setTipo(TipoPaciente tipo) { this.tipo = tipo; }

}
