package org.example.infrastructure.config;

import org.example.application.CitaRepositoryPort;
import org.example.application.UseCases.ActualizarCitaUseCase;
import org.example.application.UseCases.AgendarCitaUseCase;
import org.example.application.UseCases.ConsultarCitasUseCase;
import org.example.application.UseCases.EliminarCitaUseCase;
import org.example.domain.rules.ReglaHorarioLaboral;
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
    public AgendarCitaUseCase agendarCitaUseCase(ReglaHorarioLaboral regla, CitaRepositoryPort citaRepository) {
        return new AgendarCitaUseCase(regla, citaRepository);
    }

    @Bean
    public ConsultarCitasUseCase consultarCitasUseCase(CitaRepositoryPort citaRepository) {
        return new ConsultarCitasUseCase(citaRepository);
    }

    @Bean
    public ActualizarCitaUseCase actualizarCitaUseCase(CitaRepositoryPort citaRepository, ReglaHorarioLaboral reglaHorario) {
        return new ActualizarCitaUseCase(citaRepository, reglaHorario);
    }

    @Bean
    public EliminarCitaUseCase eliminarCitaUseCase(CitaRepositoryPort citaRepository) {
        return new EliminarCitaUseCase(citaRepository);
    }


}
