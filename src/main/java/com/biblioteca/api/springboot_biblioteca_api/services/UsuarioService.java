package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO save(UsuarioCreateDTO dto);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findById(Long id);
    UsuarioUpdateResponseDTO update(Long id, UsuarioUpdateDTO dto);
    void deleteById(Long id);
}