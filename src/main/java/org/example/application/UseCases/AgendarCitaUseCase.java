package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.application.DoctorRepositoryPort;
import org.example.application.PacienteRepositoryPort;
import org.example.application.dto.request.CrearCitaRequest;
import org.example.domain.factories.CitaFactory;
import org.example.domain.model.*;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.rules.ReglaSinEmpalmes;
import org.example.domain.services.ReglasDeAgenda;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AgendarCitaUseCase {

    private final ReglasDeAgenda reglasDeAgenda;
    private final CitaRepositoryPort citaRepository;
    private final PacienteRepositoryPort pacienteRepository;

    public AgendarCitaUseCase(ReglasDeAgenda reglasDeAgenda, CitaRepositoryPort citaRepository, PacienteRepositoryPort pacienteRepository) {
        this.citaRepository = citaRepository;
        this.reglasDeAgenda = reglasDeAgenda;
        this.pacienteRepository = pacienteRepository;

    }

    public Cita ejecutar(CrearCitaRequest citaRequest) {
        Paciente paciente = pacienteRepository.buscarPorId(citaRequest.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + citaRequest.getPacienteId()));

        Cita citaNueva = CitaFactory.crearCitaDesdeRequest(
                paciente.getId(),
                citaRequest,
                paciente.getTipo());

        reglasDeAgenda.validarCitaViable(citaNueva);
        return citaRepository.guardar(citaNueva);
    }
    public Cita obtenerPorId(UUID id) {
        return citaRepository.obtenerPorId(id);
    }


}
