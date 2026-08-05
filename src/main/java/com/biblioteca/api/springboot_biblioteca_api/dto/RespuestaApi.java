package com.biblioteca.api.springboot_biblioteca_api.dto;

public class RespuestaApi<T> {
    
    private boolean correcto;
    private String mensaje;
    private T datos;

    public RespuestaApi(boolean correcto, String mensaje, T datos) {
        this.correcto = correcto;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    } 

    
}
