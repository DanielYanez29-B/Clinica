package org.example.infrastructure.config;

import org.example.application.CitaRepositoryPort;
import org.example.application.DoctorRepositoryPort;
import org.example.application.PacienteRepositoryPort;
import org.example.application.UseCases.ActualizarCitaUseCase;
import org.example.application.UseCases.AgendarCitaUseCase;
import org.example.application.UseCases.ConsultarCitasUseCase;
import org.example.application.UseCases.EliminarCitaUseCase;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.services.ReglasDeAgenda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

@Configuration
public class ClinicaConfig {

    @Bean
    public ReglaHorarioLaboral reglaHorario(
        @Value("${clinica.horario.apertura}") String horaApertura,
        @Value("${clinica.horario.cierre}") String horaCierre) {

        return new ReglaHorarioLaboral(
                LocalTime.parse(horaApertura),
                LocalTime.parse(horaCierre)
        );
    }

    @Bean
    public ReglasDeAgenda reglasDeAgenda(ReglaHorarioLaboral reglaHorario,
                                         DoctorRepositoryPort doctorRepository,
                                         CitaRepositoryPort citaRepository) {
        return new ReglasDeAgenda(reglaHorario, doctorRepository, citaRepository);
    }


    @Bean
    public AgendarCitaUseCase agendarCitaUseCase(ReglasDeAgenda reglasDeAgenda,
                                                 CitaRepositoryPort citaRepository,
                                                 PacienteRepositoryPort pacienteRepository) {
        return new AgendarCitaUseCase(reglasDeAgenda, citaRepository, pacienteRepository);
    }

    @Bean
    public ConsultarCitasUseCase consultarCitasUseCase(CitaRepositoryPort citaRepository) {
        return new ConsultarCitasUseCase(citaRepository);
    }

    @Bean
    public ActualizarCitaUseCase actualizarCitaUseCase(CitaRepositoryPort citaRepository, ReglasDeAgenda reglasDeAgenda) {
        return new ActualizarCitaUseCase(citaRepository, reglasDeAgenda);
    }

    @Bean
    public EliminarCitaUseCase eliminarCitaUseCase(CitaRepositoryPort citaRepository) {
        return new EliminarCitaUseCase(citaRepository);
    }


}
