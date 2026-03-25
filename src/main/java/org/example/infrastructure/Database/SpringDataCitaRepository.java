package org.example.infrastructure.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataCitaRepository extends JpaRepository<CitaEntity, UUID> {
    List<CitaEntity> findByFechaHoraBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
}