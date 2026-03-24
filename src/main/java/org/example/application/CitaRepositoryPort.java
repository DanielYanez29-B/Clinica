package org.example.application;

import org.example.domain.model.Cita;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepositoryPort {
    Cita guardar(Cita cita);

    List<Cita> obtenerTodas();

    Cita actualizar(Long id, Cita cita);
    void eliminar(Long id);

    List<Cita> obtenerPorFecha(LocalDate fecha);
}
