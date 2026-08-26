package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Perfil;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoDuplicadoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
import com.biblioteca.api.springboot_biblioteca_api.mappers.PerfilMapper;
import com.biblioteca.api.springboot_biblioteca_api.repositories.PerfilRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;

    public PerfilServiceImpl(PerfilRepository perfilRepository, PerfilMapper perfilMapper,
            UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public PerfilResponseDTO save(PerfilCreateDTO dto) {

        if (perfilRepository.existsByTelefono(dto.telefono())) {
            throw new RecursoDuplicadoException("El telefono " + dto.telefono() + " ya esta asociado a otro perfil");
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                                            .orElseThrow(
                                                    () -> new RecursoNoEncontradoException("No existe un usuario con el id: " + dto.usuarioId()
                                                )
                                            );

        Perfil perfil = perfilMapper.toEntity(dto);
        perfil.setUsuario(usuario);

        return perfilMapper.toDto(perfilRepository.save(perfil));
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilResponseDTO findById(Long id) {
        return perfilMapper.toDto(perfilRepository.findById(id)
                                                          .orElseThrow(
                                                                () -> new RecursoNoEncontradoException("No existe un perfil con id: " + id)
                                                            )
                                                        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<PerfilResponseDTO> findAll(Pageable pageable) {

        Page<Perfil> perfiles = perfilRepository.findAll(pageable);
        Page<PerfilResponseDTO> perfilesDTO = perfiles.map(perfilMapper::toDto);

        return new PageResponseDTO<>(
                        perfilesDTO.getContent(),
                        perfilesDTO.getNumber() + 1,
                        perfilesDTO.getSize(),
                        perfilesDTO.getTotalElements(),
                        perfilesDTO.getTotalPages(),
                        perfilesDTO.isLast()
        );
    }

    @Override
    @Transactional
    public PerfilResponseDTO update(Long id, PerfilUpdateDTO dto) {

        Perfil perfil = perfilRepository.findById(id)
                                        .orElseThrow(
                                            () -> new RecursoNoEncontradoException("No existe un perfil con id: " + id)
                                        );

        if (perfilRepository.existsByTelefonoAndIdNot(dto.telefono(), id)) {
            throw new RecursoDuplicadoException("El telefono " + dto.telefono() + " ya esta asociado a otro perfil");
        }

        perfil.setTelefono(dto.telefono());
        perfil.setDireccion(dto.direccion());
        perfil.setFechaNacimiento(dto.fechaNacimiento());

        return perfilMapper.toDto(perfilRepository.save(perfil));
    }
}