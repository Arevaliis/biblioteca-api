package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;

public interface PrestamoService {
    
    PrestamoResponseDTO save(PrestamoCreateDTO dto);
    PrestamoResponseDTO findById(Long id);
    List<PrestamoResponseDTO> findAll();
    PrestamoResponseDTO devolver(Long id);
}
