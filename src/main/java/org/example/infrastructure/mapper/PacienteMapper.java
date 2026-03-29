package org.example.infrastructure.mapper;

import org.example.application.dto.request.CrearPacienteRequest;
import org.example.application.dto.response.PacienteResponse;
import org.example.domain.model.Paciente;

public class PacienteMapper {

    public static Paciente toDomain(CrearPacienteRequest request) {
        return new Paciente(request.getNombre());

    }
    public static PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getTipo()
        );
    }
}
