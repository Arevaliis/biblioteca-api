package com.biblioteca.api.springboot_biblioteca_api.services;

import org.springframework.data.domain.Pageable;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;

public interface PerfilService {
    
    PerfilResponseDTO save(PerfilCreateDTO dto);
    PerfilResponseDTO findById(Long id);
    PageResponseDTO<PerfilResponseDTO> findAll(Pageable pageable);
    PerfilResponseDTO update(Long id, PerfilUpdateDTO dto);
}
