package com.biblioteca.api.springboot_biblioteca_api.exceptions;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.PropiedadNoPermitidaException;
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/tipo-parametro", 
            HttpStatus.BAD_REQUEST.value(), 
            "Error tipo de parametro en el path " + ex.getName(),
            "Se ingreso el valor '" + ex.getValue() + "' en el path variable de {" + ex.getName() + "} y se requiere un " + ex.getRequiredType()   
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ProblemDetail> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/metodo-no-soportado", 
            HttpStatus.METHOD_NOT_ALLOWED.value(), 
            "Metodo no soportado" ,
            ex.getMessage() 
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/formato-json", 
            HttpStatus.BAD_REQUEST.value(), 
            "Json mal formado" ,
            ex.getMessage()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handlerDataIntegrityViolationException(DataIntegrityViolationException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/integridad-datos", 
            HttpStatus.CONFLICT.value(), 
            "Error de integridad de datos" ,
            "No se pudo completar la operación debido a la restricción de integridad de los datos"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handlerException(Exception ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/error-interno", 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error interno del servidor" ,
            "Se ha producido un error interno en el servidor."
        );
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ProblemDetail> handlerPropertyReferenceException(PropertyReferenceException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/parametro-no-encontrado", 
            HttpStatus.BAD_REQUEST.value(), 
            "Error de parametro" ,
            "No se pudo completar la operación debido a que el parametro '" + ex.getPropertyName() + "' no se encontro"
        );
    }

    @ExceptionHandler(PropiedadNoPermitidaException.class)
    public ResponseEntity<ProblemDetail> handlerPropiedadNoPermitidaException(PropiedadNoPermitidaException ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/errors/propiedad-no-permitida", 
            HttpStatus.BAD_REQUEST.value(), 
            "Propiedad no permitida",
            "La propiedad '" + ex.getMessage()  + "' no está permitida para ordenar"
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