package com.biblioteca.api.springboot_biblioteca_api.exceptions.common;

public class PropiedadNoPermitidaException extends RuntimeException {

    public PropiedadNoPermitidaException(String propiedad) {
        super(propiedad);
    }

}
