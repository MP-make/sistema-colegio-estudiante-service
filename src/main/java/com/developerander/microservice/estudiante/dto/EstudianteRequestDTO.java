package com.developerander.microservice.estudiante.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteRequestDTO {

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 digitos")
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no deben superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100, message = "El apellido paterno no debe superar los 100 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 100, message = "El apellido materno no debe superar los 100 caracteres")
    private String apellidoMaterno;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El genero es obligatorio")
    @Pattern(regexp = "(?i)MASCULINO|FEMENINO|OTRO", message = "El genero debe ser MASCULINO, FEMENINO u OTRO")
    private String genero;

    @NotBlank(message = "La direccion es obligatoria")
    @Size(max = 255, message = "La direccion no debe superar los 255 caracteres")
    private String direccion;

    @NotBlank(message = "El DNI del apoderado es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI del apoderado debe tener exactamente 8 digitos")
    private String apoderadoDni;

    @NotBlank(message = "El nombre completo del apoderado es obligatorio")
    @Size(max = 150, message = "El nombre del apoderado no debe superar los 150 caracteres")
    private String apoderadoNombreCompleto;

    @NotBlank(message = "El parentesco del apoderado es obligatorio")
    @Size(max = 50, message = "El parentesco no debe superar los 50 caracteres")
    private String apoderadoParentesco;

    @NotBlank(message = "El telefono del apoderado es obligatorio")
    @Pattern(regexp = "\\d{6,15}", message = "El telefono del apoderado debe contener entre 6 y 15 digitos")
    private String apoderadoTelefono;

    @NotBlank(message = "El email del apoderado es obligatorio")
    @Email(message = "El email del apoderado no tiene un formato valido")
    @Size(max = 120, message = "El email no debe superar los 120 caracteres")
    private String apoderadoEmail;
}
