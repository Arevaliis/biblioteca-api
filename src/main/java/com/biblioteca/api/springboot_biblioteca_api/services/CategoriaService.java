package com.biblioteca.api.springboot_biblioteca_api.services;

import org.springframework.data.domain.Pageable;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;

public interface CategoriaService {
    
    CategoriaResponseDTO save(CategoriaCreateDTO dto);
    PageResponseDTO<CategoriaResponseDTO> findAll(Pageable pageable);
    CategoriaResponseDTO findById(Long id);
    CategoriaResponseDTO update(Long id, CategoriaUpdateDTO dto);
    void deleteById(Long id);
}
