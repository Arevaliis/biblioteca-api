package com.biblioteca.api.springboot_biblioteca_api.services;

import org.springframework.data.domain.Pageable;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO save(UsuarioCreateDTO dto);
    PageResponseDTO<UsuarioResponseDTO> findAll(Pageable pageable);
    UsuarioResponseDTO findById(Long id);
    UsuarioUpdateResponseDTO update(Long id, UsuarioUpdateDTO dto);
    void deleteById(Long id);
}