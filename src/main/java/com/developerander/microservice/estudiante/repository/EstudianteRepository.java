package com.developerander.microservice.estudiante.repository;

import com.developerander.microservice.estudiante.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {

    Optional<EstudianteEntity> findByDni(String dni);

    boolean existsByDni(String dni);
}
