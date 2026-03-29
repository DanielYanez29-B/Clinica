package org.example.application;

import org.example.domain.model.Paciente;

import java.util.Optional;
import java.util.UUID;

public interface PacienteRepositoryPort {
    Optional<Paciente> buscarPorId(UUID id);
}
