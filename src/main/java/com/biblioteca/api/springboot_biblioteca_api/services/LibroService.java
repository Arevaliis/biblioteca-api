package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;

public interface LibroService {
    
    LibroResponseDTO save(LibroCreateDTO dto);
    LibroResponseDTO findById(Long id);
    List<LibroResponseDTO> findAll();
    LibroResponseDTO update(Long id, LibroUpdateDTO dto);
    void deleteById(Long id);
}
