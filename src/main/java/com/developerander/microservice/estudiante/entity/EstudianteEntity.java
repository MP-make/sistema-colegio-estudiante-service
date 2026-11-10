package com.developerander.microservice.estudiante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa a un alumno y los datos de su apoderado.
 *
 * <p>Por la autonomia de datos que exige la arquitectura SOA, esta entidad
 * no mantiene ninguna relacion ni llave foranea hacia el microservicio de
 * matriculas.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiantes")
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni", nullable = false, unique = true, length = 8)
    private String dni;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false, length = 20)
    private String genero;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    // Datos del apoderado
    @Column(name = "apoderado_dni", nullable = false, length = 8)
    private String apoderadoDni;

    @Column(name = "apoderado_nombre_completo", nullable = false, length = 150)
    private String apoderadoNombreCompleto;

    @Column(name = "apoderado_parentesco", nullable = false, length = 50)
    private String apoderadoParentesco;

    @Column(name = "apoderado_telefono", nullable = false, length = 15)
    private String apoderadoTelefono;

    @Column(name = "apoderado_email", nullable = false, length = 120)
    private String apoderadoEmail;

    // Auditoria / estado
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        LocalDateTime ahora = LocalDateTime.now();
        if (this.estado == null || this.estado.isBlank()) {
            this.estado = "ACTIVO";
        }
        this.fechaRegistro = ahora;
        this.fechaActualizacion = ahora;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
