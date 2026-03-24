package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.domain.model.Cita;

import java.util.List;

public class ConsultarCitasUseCase {

    private final CitaRepositoryPort citaRepository;

    public ConsultarCitasUseCase(CitaRepositoryPort citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> ejecutar() {
        return citaRepository.obtenerTodas();
    }
}
