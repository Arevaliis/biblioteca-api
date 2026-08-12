package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;

public interface CategoriaService {
    
    CategoriaResponseDTO save(CategoriaCreateDTO dto);
    List<CategoriaResponseDTO> findAll();
    CategoriaResponseDTO findById(Long id);
    CategoriaResponseDTO update(Long id, CategoriaUpdateDTO dto);
    void deleteById(Long id);
}
