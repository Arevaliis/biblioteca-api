package com.biblioteca.api.springboot_biblioteca_api.dto.perfil;

import java.time.LocalDate;

public record PerfilCreateDTO(String telefono, String direccion, LocalDate fechaNacimiento, Long usuarioId) {}
