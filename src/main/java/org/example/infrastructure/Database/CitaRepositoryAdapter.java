package org.example.infrastructure.Database;

import org.example.domain.Cita;
import org.springframework.stereotype.Component;
import org.example.domain.Cita;
import org.example.application.CitaRepositoryPort;
import org.springframework.stereotype.Service;

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
        CitaEntity entidad = new CitaEntity(cita.getNombrePaciente(), cita.getFechaHora());

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
                        entidad.getFechaHora()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Cita actualizar(Long id, Cita citaNueva) {

        return repository.findById(id).map(entidadVieja -> {
            entidadVieja.setNombrePaciente(citaNueva.getNombrePaciente());
            entidadVieja.setFechaHora(citaNueva.getFechaHora());
            CitaEntity guardada = repository.save(entidadVieja);

            return new Cita(guardada.getId(), guardada.getNombrePaciente(), guardada.getFechaHora());
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con el folio: " + id));
    }

    @Override
    public void eliminar(Long id){
        repository.deleteById(id);
    }
}
