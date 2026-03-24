package org.example.application;

import org.example.domain.Cita;

import java.util.List;

public interface CitaRepositoryPort {
    Cita guardar(Cita cita);

    List<Cita> obtenerTodas();

    Cita actualizar(Long id, Cita cita);
    void eliminar(Long id);


}
