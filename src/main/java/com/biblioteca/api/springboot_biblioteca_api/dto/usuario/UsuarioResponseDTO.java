package com.biblioteca.api.springboot_biblioteca_api.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(Long id, String nombre, String email, LocalDateTime fechaRegistro) {}