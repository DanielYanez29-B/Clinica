package org.example.domain.model;

import org.example.domain.model.valueobjects.VentanaDisponibilidad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Doctor {
    private UUID id;
    private String nombre;
    private Especialidad especialidad;

    private List<VentanaDisponibilidad> disponibilidadSemanal;

    public Doctor(UUID id, String nombre, Especialidad especialidad, List<VentanaDisponibilidad> disponibilidadSemanal) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponibilidadSemanal = disponibilidadSemanal;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public List<VentanaDisponibilidad> getDisponibilidadSemanal() {
        return disponibilidadSemanal;
    }

    public boolean tieneEspecialidad(Especialidad especialidadRequerida) {
        return this.especialidad == especialidadRequerida;
    }

    public void validarDisponibilidad(LocalDateTime inicioCita, LocalDateTime finCita) {
        boolean estaEnTurno = disponibilidadSemanal.stream()
                .anyMatch(ventana -> ventana.cubreElhorarioDe(inicioCita, finCita));
        if (!estaEnTurno) {
            throw new IllegalArgumentException("El doctor no está disponible en el horario solicitado.");
        }
    }

}
