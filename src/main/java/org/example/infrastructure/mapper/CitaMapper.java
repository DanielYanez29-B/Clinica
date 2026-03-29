package org.example.infrastructure.mapper;

import org.example.application.dto.request.ActualizarCitaRequest;
import org.example.application.dto.request.CrearCitaRequest;
import org.example.application.dto.response.CitaResponse;
import org.example.domain.model.Cita;
import org.example.domain.model.Especialidad;
import org.example.domain.model.EstadoCita;

import java.time.LocalDateTime;
import java.util.UUID;

public class CitaMapper {

    public static Cita toDomainParaActualizar(ActualizarCitaRequest request) {
        // Crear una Cita parcial solo con los datos a actualizar
        Cita cita = new Cita();  // Constructor vacío

        cita.setFechaHora(request.getFechaHora());
        cita.setEspecialidad(request.getEspecialidad());
        cita.setEstadoCita(request.getEstadoCita());
        cita.setDoctorId(request.getDoctorId());

        return cita;
    }

    public static CitaResponse toResponse(Cita cita) {
        return new CitaResponse(
                cita.getId(),
                cita.getPacienteId(),
                cita.getFechaHora(),
                cita.getFechaHoraFin(),
                cita.getEspecialidad(),
                cita.getEstadoCita(),
                cita.getDoctorId(),
                cita.getRecursoFisicoId()
        );
    }
}