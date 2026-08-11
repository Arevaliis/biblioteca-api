package com.biblioteca.api.springboot_biblioteca_api.dto.prestamo;

import java.time.LocalDateTime;

public record PrestamoResponseDTO( Long id, LocalDateTime fechaPrestamo, LocalDateTime fechaDevolucion, Long idUsuario ) {} 