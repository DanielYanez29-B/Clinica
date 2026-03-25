package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.domain.model.Cita;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.rules.ReglaSinEmpalmes;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


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

        List<Cita> citasDelDia = citaRepository.obtenerPorFecha(cita.getFechaHora().toLocalDate());
        new ReglaSinEmpalmes().validar(cita, citasDelDia);

        Cita citaConEstado = new Cita(
                cita.getPaciente(),
                cita.getFechaHora(),
                cita.getEspecialidad()
        );

        return citaRepository.guardar(citaConEstado);
    }
    public Cita obtenerPorId(UUID id) {
        return citaRepository.obtenerPorId(id);
    }


}
