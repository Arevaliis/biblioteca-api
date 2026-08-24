package com.biblioteca.api.springboot_biblioteca_api.exceptions;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoDuplicadoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.prestamo.PrestamoYaDevueltoException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ProblemDetail> handlerRecursoNoEncontrado(RecursoNoEncontradoException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/recurso-no-encontrado", 
            HttpStatus.NOT_FOUND.value(), 
            "Recurso no encontrado", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ProblemDetail> handlerRecursoDuplicado(RecursoDuplicadoException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/recurso-duplicado", 
            HttpStatus.CONFLICT.value(), 
            "Recurso duplicado", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(PrestamoYaDevueltoException.class)
    public ResponseEntity<ProblemDetail> handlerPrestamoYaDevueltoException(PrestamoYaDevueltoException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/prestamo-ya-devuelto", 
            HttpStatus.CONFLICT.value(), 
            "Prestamo ya devuelto", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/validacion", 
            HttpStatus.BAD_REQUEST.value(), 
            "Error de Validacion" ,
            "Los datos enviados no son válidos", 
            ex.getBindingResult()    
        );
    }

    private ResponseEntity<ProblemDetail> buildProblemDetail(
        String uri,
        int status,
        String titulo,
        String message
    ) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setType(URI.create(uri));
        problemDetail.setTitle(titulo);
        problemDetail.setDetail(message);
        problemDetail.setProperty("date", LocalDateTime.now());

        return ResponseEntity.status(status).body(problemDetail);
    }

    private ResponseEntity<ProblemDetail> buildProblemDetail(
        String uri,
        int status,
        String titulo,
        String message,
        BindingResult result
    ) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setType(URI.create(uri));
        problemDetail.setTitle(titulo);
        problemDetail.setProperty("date", LocalDateTime.now());

        Map<String, List<String>> errors = new HashMap<>();
        
        result.getFieldErrors()
              .forEach(
                    err -> {
                        errors.computeIfAbsent(err.getField(), key -> new ArrayList<>());
                        errors.get(err.getField()).add(err.getDefaultMessage());
                }
            );

        problemDetail.setDetail(message);
        problemDetail.setProperty("errors", errors );

        return ResponseEntity.status(status).body(problemDetail);
    }
}