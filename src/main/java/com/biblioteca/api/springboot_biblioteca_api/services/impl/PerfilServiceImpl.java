package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Perfil;
import com.biblioteca.api.springboot_biblioteca_api.mappers.PerfilMapper;
import com.biblioteca.api.springboot_biblioteca_api.repositories.PerfilRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;

    public PerfilServiceImpl(PerfilRepository perfilRepository, PerfilMapper perfilMapper, UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public PerfilResponseDTO save(PerfilCreateDTO dto) {
        Perfil perfil = perfilMapper.toEntity(dto);
        perfil.setUsuario(usuarioRepository.findById(dto.usuarioId()).orElseThrow());

        return perfilMapper.toDto(perfilRepository.save(perfil));
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilResponseDTO findById(Long id) {
        return perfilMapper.toDto(perfilRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerfilResponseDTO> findAll() {
        return perfilRepository.findAll().stream().map(perfilMapper::toDto).toList();
    }

    @Override
    @Transactional
    public PerfilResponseDTO update(Long id, PerfilUpdateDTO dto) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow();

        perfil.setTelefono(dto.telefono());
        perfil.setDireccion(dto.direccion());
        perfil.setFechaNacimiento(dto.fechaNacimiento());

        return perfilMapper.toDto(perfilRepository.save(perfil));
    }
}