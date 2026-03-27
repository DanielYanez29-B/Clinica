package org.example.infrastructure.Database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataDoctorRepository extends JpaRepository<DoctorEntity, UUID> {

}
