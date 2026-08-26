package com.biblioteca.api.springboot_biblioteca_api.services;

import org.springframework.data.domain.Pageable;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;

public interface PrestamoService {
    
    PrestamoResponseDTO save(PrestamoCreateDTO dto);
    PrestamoResponseDTO findById(Long id);
    PageResponseDTO<PrestamoResponseDTO> findAll(Pageable pageable);
    PrestamoResponseDTO devolver(Long id);
}
