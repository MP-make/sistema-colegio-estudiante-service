package com.developerander.microservice.estudiante.controller;

import com.developerander.microservice.estudiante.dto.EstudianteRequestDTO;
import com.developerander.microservice.estudiante.dto.EstudianteResponseDTO;
import com.developerander.microservice.estudiante.service.EstudianteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteResponseDTO>> listar() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<EstudianteResponseDTO> obtenerPorDni(
            @PathVariable
            @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 digitos")
            String dni) {
        return ResponseEntity.ok(estudianteService.obtenerPorDni(dni));
    }

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> crear(@Valid @RequestBody EstudianteRequestDTO requestDTO) {
        EstudianteResponseDTO creado = estudianteService.crearEstudiante(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
