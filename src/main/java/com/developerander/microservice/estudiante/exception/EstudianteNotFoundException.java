package com.developerander.microservice.estudiante.exception;

/**
 * Se lanza cuando no existe un estudiante para el identificador solicitado.
 */
public class EstudianteNotFoundException extends RuntimeException {

    public EstudianteNotFoundException(String message) {
        super(message);
    }
}
