package com.developerander.microservice.estudiante.exception;

/**
 * Se lanza cuando se intenta registrar un estudiante con un DNI ya existente.
 */
public class DniDuplicadoException extends RuntimeException {

    public DniDuplicadoException(String dni) {
        super("Ya existe un estudiante registrado con el DNI " + dni);
    }
}
