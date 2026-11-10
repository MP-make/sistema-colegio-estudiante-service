package com.developerander.microservice.estudiante.service;

import com.developerander.microservice.estudiante.dto.EstudianteRequestDTO;
import com.developerander.microservice.estudiante.dto.EstudianteResponseDTO;

import java.util.List;

public interface EstudianteService {

    List<EstudianteResponseDTO> listarEstudiantes();

    EstudianteResponseDTO obtenerPorId(Long id);

    EstudianteResponseDTO obtenerPorDni(String dni);

    EstudianteResponseDTO crearEstudiante(EstudianteRequestDTO requestDTO);
}
