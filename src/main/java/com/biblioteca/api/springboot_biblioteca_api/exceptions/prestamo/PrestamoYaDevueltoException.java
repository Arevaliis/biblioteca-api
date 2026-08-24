package com.biblioteca.api.springboot_biblioteca_api.exceptions.prestamo;

public class PrestamoYaDevueltoException extends RuntimeException {

    public PrestamoYaDevueltoException(String message) {
        super(message);
    }
}
