package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;

public class EliminarCitaUseCase {
    private final CitaRepositoryPort citaRepository;

    public EliminarCitaUseCase(CitaRepositoryPort citaRepository) {
        this.citaRepository = citaRepository;
    }

    public void ejecutar(Long id) {
        citaRepository.eliminar(id);
    }

}
