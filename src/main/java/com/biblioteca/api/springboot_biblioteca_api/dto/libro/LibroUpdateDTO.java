package com.biblioteca.api.springboot_biblioteca_api.dto.libro;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LibroUpdateDTO(
    
    @NotBlank
    @Size(max = 100)
    String titulo, 
    
    @NotBlank
    @Size(max=50)
    String autor, 
    
    @NotEmpty
    List<@Positive Long> categoriasIds

) {}