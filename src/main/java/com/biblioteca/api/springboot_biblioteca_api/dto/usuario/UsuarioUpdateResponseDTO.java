package com.biblioteca.api.springboot_biblioteca_api.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioUpdateResponseDTO(Long id, String nombre, String email, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {}