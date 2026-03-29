package org.example.application.dto.request;

public class CrearPacienteRequest {
    private String nombre;

    public CrearPacienteRequest() {}

    public CrearPacienteRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }


}
