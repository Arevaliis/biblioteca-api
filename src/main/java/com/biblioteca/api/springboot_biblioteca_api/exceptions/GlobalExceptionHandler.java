package com.biblioteca.api.springboot_biblioteca_api.exceptions;

import java.net.URI;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
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

    private ResponseEntity<ProblemDetail> buildProblemDetail(
        String uri,
        int status,
        String error,
        String message
    ) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setType(URI.create(uri));
        problemDetail.setTitle(error);
        problemDetail.setDetail(message);
        problemDetail.setProperty("date", new Date());

        return ResponseEntity.status(status).body(problemDetail);
    }
}
