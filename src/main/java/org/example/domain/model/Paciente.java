package org.example.domain.model;

import java.util.UUID;

public class Paciente {

    private UUID id;
    private String nombre;
    private TipoPaciente tipo;

    public Paciente() {
    }

    public Paciente(String nombre) {
        this.nombre = nombre;
        this.tipo = TipoPaciente.NUEVO;
    }

    public Paciente(UUID id, String nombre, TipoPaciente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoPaciente getTipo() { return tipo; }
    public void setTipo(TipoPaciente tipo) { this.tipo = tipo; }
}