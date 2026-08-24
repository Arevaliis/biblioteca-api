package com.biblioteca.api.springboot_biblioteca_api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCreateDTO(
    
    @NotBlank(message = "{categoria.nombre.notblank}")
    @Size(min = 4, max = 50, message = "{categoria.nombre.size}")
    String nombre

) { }