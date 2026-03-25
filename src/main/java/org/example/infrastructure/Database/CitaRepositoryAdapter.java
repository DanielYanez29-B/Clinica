package org.example.infrastructure.Database;

import org.example.domain.model.Cita;
import org.example.application.CitaRepositoryPort;
import org.example.domain.model.Paciente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CitaRepositoryAdapter implements CitaRepositoryPort {
    private final SpringDataCitaRepository repository;

    public CitaRepositoryAdapter(SpringDataCitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cita guardar(Cita cita) {
        PacienteEntity pacienteEntity = new PacienteEntity(
                cita.getPaciente().getId(),
                cita.getPaciente().getNombre(),
                cita.getPaciente().getTipo()
        );

        CitaEntity entity = new CitaEntity(
                cita.getId(),
                pacienteEntity,
                cita.getFechaHora(),
                cita.getEspecialidad()
        );

        CitaEntity guardada = repository.save(entity);
        return mapearADominio(guardada);
    }

    @Override
    public List<Cita> obtenerPorFecha(LocalDate fecha) {
        LocalDateTime inicioDia = fecha.atStartOfDay();
        LocalDateTime finDia = fecha.atTime(LocalTime.MAX);

        List<CitaEntity> entities = repository.findByFechaHoraBetween(inicioDia, finDia);

        return entities.stream()
                .map(this::mapearADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> obtenerTodas() {
        return repository.findAll().stream().map(this::mapearADominio).collect(Collectors.toList());
    }

    @Override
    public Cita obtenerPorId(UUID id) {
        Optional<CitaEntity> entidad = repository.findById(id);
        return entidad.map(this::mapearADominio).orElse(null);
    }

    @Override
    public Cita actualizar(UUID id, Cita cita) {
        // La lógica de actualizar la revisaremos después, por ahora que compile:
        return guardar(cita);
    }

    @Override
    public void eliminar(UUID id) {
        repository.deleteById(id);
    }

    private Cita mapearADominio(CitaEntity entidad) {
        Paciente paciente = new Paciente(
                entidad.paciente().getId(),
                entidad.paciente().getNombre(),
                entidad.paciente().getTipo()
        );

        return new Cita(
                entidad.getId(),
                paciente,
                entidad.getFechaHora(),
                entidad.getEspecialidad()
        );
    }




}