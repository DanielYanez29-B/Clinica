package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.domain.model.Cita;
import org.example.domain.rules.ReglaHorarioLaboral;

import java.util.UUID;

public class ActualizarCitaUseCase {
    private final CitaRepositoryPort citaRepository;
    private final ReglaHorarioLaboral reglaHorario;

    public ActualizarCitaUseCase(CitaRepositoryPort citaRepository, ReglaHorarioLaboral reglaHorario) {
        this.citaRepository = citaRepository;
        this.reglaHorario = reglaHorario;
    }

    public Cita ejecutar(UUID id, Cita cita) {
        reglaHorario.validar(cita.getFechaHora().toLocalTime());
        cita.setId(id);
        return citaRepository.actualizar(id, cita);
    }
}
