package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoDuplicadoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
import com.biblioteca.api.springboot_biblioteca_api.mappers.UsuarioMapper;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO save(UsuarioCreateDTO dto) {

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RecursoDuplicadoException("El email " + dto.email() + " ya existe.");
        }
        
        Usuario usuarioCreado = usuarioRepository.save(usuarioMapper.toEntity(dto));

        return usuarioMapper.toDTO(usuarioCreado);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<UsuarioResponseDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);    
        Page<UsuarioResponseDTO> usuariosDTO =  usuarios.map(usuarioMapper::toDTO);
                                                                        
        return new PageResponseDTO<>(
                        usuariosDTO.getContent(),
                        usuariosDTO.getNumber() + 1,
                        usuariosDTO.getSize(),
                        usuariosDTO.getTotalElements(),
                        usuariosDTO.getTotalPages(),
                        usuariosDTO.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                                            .orElseThrow(
                                                () -> new RecursoNoEncontradoException("No existe un usuario con el id: " + id)
                                            ); 

        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioUpdateResponseDTO update(Long id, UsuarioUpdateDTO dto) {
        
        Usuario oldUsuario = usuarioRepository.findById(id)
                                              .orElseThrow(
                                                () -> new RecursoNoEncontradoException("No existe un usuario con el id: " + id)
                                            ); 

        if (usuarioRepository.existsByEmailAndIdNot(dto.email(), id)){
            throw new RecursoDuplicadoException("El email " + dto.email() + " ya esta registrado a nombre de otro usuario.");
        }

        oldUsuario.setNombre(dto.nombre());
        oldUsuario.setEmail(dto.email());

        return usuarioMapper.toUpdateDTO( usuarioRepository.save(oldUsuario) );
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.findById(id)
                         .orElseThrow(
                            () -> new RecursoNoEncontradoException("No existe un usuario con el id: " + id)
                        ); 
                         
        usuarioRepository.deleteById(id);
    }

}
