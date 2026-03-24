package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.domain.Cita;
import org.example.domain.rules.ReglaHorarioLaboral;

public class ActualizarCitaUseCase {
    private final CitaRepositoryPort citaRepository;
    private final ReglaHorarioLaboral reglaHorario;

    public ActualizarCitaUseCase(CitaRepositoryPort citaRepository, ReglaHorarioLaboral reglaHorario) {
        this.citaRepository = citaRepository;
        this.reglaHorario = reglaHorario;
    }

    public Cita ejecutar(Long id, Cita cita) {
        reglaHorario.validar(cita.getFechaHora().toLocalTime());
        return citaRepository.actualizar(id, cita);
    }
}
