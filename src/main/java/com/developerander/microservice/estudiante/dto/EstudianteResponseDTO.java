package com.developerander.microservice.estudiante.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteResponseDTO {

    private Long id;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;

    private String apoderadoDni;
    private String apoderadoNombreCompleto;
    private String apoderadoParentesco;
    private String apoderadoTelefono;
    private String apoderadoEmail;

    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}
