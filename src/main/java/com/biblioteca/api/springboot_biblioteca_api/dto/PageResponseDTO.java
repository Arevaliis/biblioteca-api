package com.biblioteca.api.springboot_biblioteca_api.dto;

import java.util.List;

public record PageResponseDTO<T>(
    List<T> contenido,
    int pagina,
    int tamano,
    long totalElementos,
    int totalPaginas,
    boolean ultima
) {}