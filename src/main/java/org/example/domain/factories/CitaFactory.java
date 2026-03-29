package org.example.domain.factories;

import org.example.application.dto.request.CrearCitaRequest;
import org.example.domain.model.Cita;
import org.example.domain.model.EstadoCita;
import org.example.domain.model.TipoPaciente;

import java.util.UUID;

public class CitaFactory {
    public static Cita crearCitaDesdeRequest(UUID pacienteId,
                                            CrearCitaRequest citaRequest,
                                            TipoPaciente tipoPaciente) {

        EstadoCita estado = (tipoPaciente == TipoPaciente.NUEVO)
                ? EstadoCita.PAGADA
                : EstadoCita.PENDIENTE;
        return new Cita(
                pacienteId,
                citaRequest.getFechaHora(),
                citaRequest.getEspecialidad(),
                citaRequest.getDoctorId(),
                citaRequest.getRecursoFisicoId(),
                estado
        );
    }
}
