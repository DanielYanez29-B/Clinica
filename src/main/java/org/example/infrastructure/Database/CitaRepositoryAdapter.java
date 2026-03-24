package org.example.infrastructure.Database;

import org.example.domain.model.Cita;
import org.example.application.CitaRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaRepositoryAdapter implements CitaRepositoryPort {
    private final SpringDataCitaRepository repository;

    public CitaRepositoryAdapter(SpringDataCitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cita guardar(Cita cita) {
        CitaEntity entidad = new CitaEntity(cita.getNombrePaciente(), cita.getFechaHora(), cita.getEspecialidad());

        CitaEntity entidadGuardada = repository.save(entidad);

        cita.setId(entidadGuardada.getId());
        return cita;
    }

    @Override
    public List<Cita> obtenerTodas() {
        List<CitaEntity> entidades = repository.findAll();
        return entidades.stream()
                .map(entidad -> new Cita(
                        entidad.getId(),
                        entidad.getNombrePaciente(),
                        entidad.getFechaHora(),
                        entidad.getEspecialidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Cita actualizar(Long id, Cita citaNueva) {

        return repository.findById(id).map(entidadVieja -> {
            entidadVieja.setNombrePaciente(citaNueva.getNombrePaciente());
            entidadVieja.setFechaHora(citaNueva.getFechaHora());
            entidadVieja.setEspecialidad(citaNueva.getEspecialidad());
            CitaEntity guardada = repository.save(entidadVieja);

            return new Cita(guardada.getId(), guardada.getNombrePaciente(), guardada.getFechaHora(), guardada.getEspecialidad());
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con el folio: " + id));
    }

    @Override
    public void eliminar(Long id){
        repository.deleteById(id);
    }

    @Override
    public List<Cita> obtenerPorFecha(LocalDate fecha) {
        java.time.LocalDateTime inicioDia = fecha.atStartOfDay();
        java.time.LocalDateTime finDia = fecha.atTime(java.time.LocalTime.MAX);

        List<CitaEntity> entidades = repository.findByFechaHoraBetween(inicioDia, finDia);
        return entidades.stream()
                .map(entidad -> new Cita(
                        entidad.getId(),
                        entidad.getNombrePaciente(),
                        entidad.getFechaHora(),
                        entidad.getEspecialidad()
                ))
                .collect(Collectors.toList());
    }
}
