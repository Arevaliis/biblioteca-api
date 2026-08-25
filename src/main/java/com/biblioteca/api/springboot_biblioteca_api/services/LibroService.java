package com.biblioteca.api.springboot_biblioteca_api.services;


import org.springframework.data.domain.Pageable;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;

public interface LibroService {
    
    LibroResponseDTO save(LibroCreateDTO dto);
    LibroResponseDTO findById(Long id);
    PageResponseDTO<LibroResponseDTO> findAll(Pageable pageable);
    LibroResponseDTO update(Long id, LibroUpdateDTO dto);
    void deleteById(Long id);
}
