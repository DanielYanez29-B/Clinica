package org.example.application;

import org.example.domain.model.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CitaRepositoryPort {
    Cita guardar(Cita cita);

    List<Cita> obtenerTodas();

    Cita actualizar(UUID id, Cita cita);
    void eliminar(UUID id);

    public Cita obtenerPorId(UUID id);
    List<Cita> obtenerPorFecha(LocalDate fecha);
}
