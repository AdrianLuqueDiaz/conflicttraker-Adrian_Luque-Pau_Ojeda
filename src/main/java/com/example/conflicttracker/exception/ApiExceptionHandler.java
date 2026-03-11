package com.example.conflicttracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(ResourceNotFoundException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcionGenerica(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
