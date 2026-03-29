package org.example.infrastructure.Database;

import org.example.application.PacienteRepositoryPort;
import org.example.domain.model.Paciente;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PacienteRepositoryAdapter implements PacienteRepositoryPort {
    private final SpringDataPacienteAdapter pacienteJpaRepository;

    public PacienteRepositoryAdapter(SpringDataPacienteAdapter pacienteJpaRepository) {
        this.pacienteJpaRepository = pacienteJpaRepository;
    }

    @Override
    public Optional<Paciente> buscarPorId(UUID id) {
        Optional<PacienteEntity> entityOptional = pacienteJpaRepository.findById(id);
    return entityOptional.map(this::toDomain);
    }

    private Paciente toDomain(PacienteEntity entity) {
        if (entity == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setId(entity.getId());
        paciente.setNombre(entity.getNombre());
        paciente.setTipo(entity.getTipo());

        return paciente;
    }



}
