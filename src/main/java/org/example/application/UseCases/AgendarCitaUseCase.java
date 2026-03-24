package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.domain.Cita;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


public class AgendarCitaUseCase {

    private final ReglaHorarioLaboral reglaHorario;
    private final CitaRepositoryPort citaRepository;

    public AgendarCitaUseCase(ReglaHorarioLaboral reglaHorario, CitaRepositoryPort citaRepository) {
        this.citaRepository = citaRepository;
        this.reglaHorario = reglaHorario;
    }

    public Cita ejecutar(Cita cita) {
        LocalTime hora = cita.getFechaHora().toLocalTime();
        reglaHorario.validar(hora);

        return citaRepository.guardar(cita);
    }
}
