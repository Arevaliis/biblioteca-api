package com.biblioteca.api.springboot_biblioteca_api.mappers;

import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;

@Component
public class PrestamoMapper {
    
    public PrestamoResponseDTO toDto(Prestamo prestamo){
        return new PrestamoResponseDTO(prestamo.getId(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(), prestamo.getUsuario().getId());
    } 
}
