package com.biblioteca.api.springboot_biblioteca_api.dto.libro;

import java.util.List;

public record LibroCreateDTO(String titulo, String autor, List<Long> categoriasId) {}
