package com.biblioteca.api.springboot_biblioteca_api.dto.prestamo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PrestamoCreateDTO(
    
    @NotNull
    @Positive
    Long usuarioId

) {}
