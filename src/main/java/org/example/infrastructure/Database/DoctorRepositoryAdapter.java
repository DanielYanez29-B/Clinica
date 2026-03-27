package org.example.infrastructure.Database;

import org.example.application.DoctorRepositoryPort;
import org.example.domain.model.Doctor;
import org.example.domain.model.valueobjects.VentanaDisponibilidad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DoctorRepositoryAdapter implements DoctorRepositoryPort {
    private final SpringDataDoctorRepository jpaRepository;

    public DoctorRepositoryAdapter(SpringDataDoctorRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Doctor> buscarPorId(UUID id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }
    private Doctor toDomain(DoctorEntity entity) {
        List<VentanaDisponibilidad> ventanaDeDisponibilidad = entity.getDisponibilidades().stream()
                .map(emb -> new VentanaDisponibilidad(
                        emb.getDiaSemana(),
                        emb.getHoraInicioTurno(),
                        emb.getHoraFinTurno()
                ))
                .collect(Collectors.toList());

        return new Doctor(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecialidad(),
                ventanaDeDisponibilidad
        );
    }
}
