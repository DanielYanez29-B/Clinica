package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.application.dto.request.ActualizarCitaRequest;
import org.example.domain.model.Cita;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.services.ReglasDeAgenda;

import java.util.UUID;

public class ActualizarCitaUseCase {
    private final CitaRepositoryPort citaRepository;
    private final ReglasDeAgenda reglasDeAgenda;

    public ActualizarCitaUseCase(CitaRepositoryPort citaRepository,
                                 ReglasDeAgenda reglasDeAgenda) {
        this.citaRepository = citaRepository;
        this.reglasDeAgenda = reglasDeAgenda;
    }

    public Cita ejecutar(UUID id, ActualizarCitaRequest citaRequest) {
        Cita citaExistente = citaRepository.obtenerPorId(id);

        citaExistente.setFechaHora(citaRequest.getFechaHora());
        citaExistente.setEspecialidad(citaRequest.getEspecialidad());
        citaExistente.setDoctorId(citaRequest.getDoctorId());
        citaExistente.setRecursoFisicoId(citaRequest.getRecursoFisicoId());
        citaExistente.setDoctorId(citaRequest.getDoctorId());

        reglasDeAgenda.validarCitaViable(citaExistente);

        return citaRepository.actualizar(id, citaExistente);
    }
}
