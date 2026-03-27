package org.example.application;

import org.example.domain.model.Doctor;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepositoryPort {
    Optional<Doctor> buscarPorId(UUID id);
}
