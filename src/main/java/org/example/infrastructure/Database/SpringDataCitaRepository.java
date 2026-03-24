package org.example.infrastructure.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataCitaRepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByFechaHoraBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
}