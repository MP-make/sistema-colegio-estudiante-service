package com.developerander.microservice.estudiante.service.impl;

import com.developerander.microservice.estudiante.dto.EstudianteRequestDTO;
import com.developerander.microservice.estudiante.dto.EstudianteResponseDTO;
import com.developerander.microservice.estudiante.entity.EstudianteEntity;
import com.developerander.microservice.estudiante.exception.DniDuplicadoException;
import com.developerander.microservice.estudiante.exception.EstudianteNotFoundException;
import com.developerander.microservice.estudiante.repository.EstudianteRepository;
import com.developerander.microservice.estudiante.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> listarEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteResponseDTO obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new EstudianteNotFoundException(
                        "No existe un estudiante con el id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteResponseDTO obtenerPorDni(String dni) {
        return estudianteRepository.findByDni(dni)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new EstudianteNotFoundException(
                        "No existe un estudiante con el DNI " + dni));
    }

    @Override
    @Transactional
    public EstudianteResponseDTO crearEstudiante(EstudianteRequestDTO requestDTO) {
        if (estudianteRepository.existsByDni(requestDTO.getDni())) {
            throw new DniDuplicadoException(requestDTO.getDni());
        }

        EstudianteEntity entity = toEntity(requestDTO);
        EstudianteEntity guardado = estudianteRepository.save(entity);
        return toResponseDTO(guardado);
    }

    private EstudianteEntity toEntity(EstudianteRequestDTO dto) {
        return EstudianteEntity.builder()
                .dni(dto.getDni())
                .nombres(dto.getNombres())
                .apellidoPaterno(dto.getApellidoPaterno())
                .apellidoMaterno(dto.getApellidoMaterno())
                .fechaNacimiento(dto.getFechaNacimiento())
                .genero(dto.getGenero().toUpperCase())
                .direccion(dto.getDireccion())
                .apoderadoDni(dto.getApoderadoDni())
                .apoderadoNombreCompleto(dto.getApoderadoNombreCompleto())
                .apoderadoParentesco(dto.getApoderadoParentesco())
                .apoderadoTelefono(dto.getApoderadoTelefono())
                .apoderadoEmail(dto.getApoderadoEmail())
                .estado("ACTIVO")
                .build();
    }

    private EstudianteResponseDTO toResponseDTO(EstudianteEntity entity) {
        return EstudianteResponseDTO.builder()
                .id(entity.getId())
                .dni(entity.getDni())
                .nombres(entity.getNombres())
                .apellidoPaterno(entity.getApellidoPaterno())
                .apellidoMaterno(entity.getApellidoMaterno())
                .fechaNacimiento(entity.getFechaNacimiento())
                .genero(entity.getGenero())
                .direccion(entity.getDireccion())
                .apoderadoDni(entity.getApoderadoDni())
                .apoderadoNombreCompleto(entity.getApoderadoNombreCompleto())
                .apoderadoParentesco(entity.getApoderadoParentesco())
                .apoderadoTelefono(entity.getApoderadoTelefono())
                .apoderadoEmail(entity.getApoderadoEmail())
                .estado(entity.getEstado())
                .fechaRegistro(entity.getFechaRegistro())
                .fechaActualizacion(entity.getFechaActualizacion())
                .build();
    }
}
