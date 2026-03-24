package org.example.infrastructure.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCitaRepository extends JpaRepository<CitaEntity, Long> {
}
