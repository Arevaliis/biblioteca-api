package com.biblioteca.api.springboot_biblioteca_api.dto.libro;

import java.util.List;

public record LibroResponseDTO(Long id, String titulo, String autor, List<String> categorias) { }