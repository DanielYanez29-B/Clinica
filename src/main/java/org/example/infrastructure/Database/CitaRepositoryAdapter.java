package org.example.infrastructure.Database;

import org.example.domain.exception.CitaNoEncontradaException;
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
    private final SpringDataPacienteAdapter pacienteRepository;

    public CitaRepositoryAdapter(SpringDataCitaRepository repository,
                                 SpringDataPacienteAdapter pacienteRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Cita guardar(Cita cita) {
        PacienteEntity pacienteProxy = pacienteRepository.getReferenceById(cita.getPacienteId());

        CitaEntity entity = new CitaEntity(
                cita.getId(),
                pacienteProxy,
                cita.getFechaHora(),
                cita.getEspecialidad(),
                cita.getEstadoCita(),
                cita.getDoctorId(),
                cita.getRecursoFisicoId()

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
        Optional<CitaEntity> entidadExistente = repository.findById(id);
        if (entidadExistente.isEmpty()) {
            throw new CitaNoEncontradaException("Cita con ID " + id + " no encontrada.");
        }

        CitaEntity entity = entidadExistente.get();

        entity.setFechaHora(cita.getFechaHora());
        entity.setEspecialidad(cita.getEspecialidad());
        entity.setEstadoCita(cita.getEstadoCita());
        entity.setDoctorId(cita.getDoctorId());
        entity.setRecursoFisicoId(cita.getRecursoFisicoId());

        if (!entity.paciente().getId().equals(cita.getPacienteId())) {
            entity.setPaciente(pacienteRepository.getReferenceById(cita.getPacienteId()));
        }

        CitaEntity actualizada = repository.save(entity);
        return mapearADominio(actualizada);
    }

    @Override
    public void eliminar(UUID id) {
        repository.deleteById(id);
    }

    private Cita mapearADominio(CitaEntity entidad) {
        Cita citaDominio = new Cita(
                entidad.getId(),
                entidad.paciente().getId(),
                entidad.getFechaHora(),
                entidad.getEspecialidad(),
                entidad.getDoctorId(),
                entidad.getRecursoFisicoId(),
                entidad.getEstadoCita()
        );
        citaDominio.setEstadoCita(entidad.getEstadoCita());

        return citaDominio;
    }




}