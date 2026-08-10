package com.biblioteca.api.springboot_biblioteca_api.dto.perfil;

import java.time.LocalDate;

public record PerfilUpdateDTO(String telefono, String direccion, LocalDate fechaNacimiento) { }