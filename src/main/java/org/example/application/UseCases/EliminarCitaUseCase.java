package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;

import java.util.UUID;

public class EliminarCitaUseCase {
    private final CitaRepositoryPort citaRepository;

    public EliminarCitaUseCase(CitaRepositoryPort citaRepository) {
        this.citaRepository = citaRepository;
    }

    public void ejecutar(UUID id) {
        citaRepository.eliminar(id);
    }

}
