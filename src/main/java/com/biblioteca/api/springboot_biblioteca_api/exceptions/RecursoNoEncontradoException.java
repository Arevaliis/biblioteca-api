package com.biblioteca.api.springboot_biblioteca_api.exceptions;

public class RecursoNoEncontradoException extends RuntimeException{

    public RecursoNoEncontradoException(String message) {
        super(message);
    }
    
}
