package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;

public interface PerfilService {
    
    PerfilResponseDTO save(PerfilCreateDTO dto);
    PerfilResponseDTO findById(Long id);
    List<PerfilResponseDTO> findAll();
    PerfilResponseDTO update(Long id, PerfilUpdateDTO dto);
}
