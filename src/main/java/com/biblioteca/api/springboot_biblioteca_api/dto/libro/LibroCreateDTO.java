package com.biblioteca.api.springboot_biblioteca_api.dto.libro;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LibroCreateDTO(
    
    @NotBlank(message = "{libro.titulo.notblank}")
    @Size(max = 100, message = "{libro.titulo.size}")
    String titulo, 
    
    @NotBlank(message = "{libro.autor.notblank}")
    @Size(max=50, message = "{libro.autor.size}")
    String autor, 
    
    @NotEmpty(message = "{libro.categoriasIds.notempty}")
    List<@Positive(message = "{libro.categoriasIds.positive}") Long> categoriasIds

) {}
