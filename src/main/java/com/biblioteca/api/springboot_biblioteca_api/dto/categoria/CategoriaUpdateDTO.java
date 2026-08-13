package com.biblioteca.api.springboot_biblioteca_api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaUpdateDTO(
    
    @NotBlank
    @Size(max = 50)
    String nombre

) { }