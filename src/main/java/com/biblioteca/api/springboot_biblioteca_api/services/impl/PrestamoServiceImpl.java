package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import com.biblioteca.api.springboot_biblioteca_api.mappers.PrestamoMapper;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.prestamo.PrestamoYaDevueltoException;
import com.biblioteca.api.springboot_biblioteca_api.repositories.PrestamoRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PrestamoMapper prestamoMapper;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,  UsuarioRepository usuarioRepository, PrestamoMapper prestamoMapper) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.prestamoMapper = prestamoMapper;
    }

    @Override
    @Transactional
    public PrestamoResponseDTO save(PrestamoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                                           .orElseThrow( 
                                            () -> new RecursoNoEncontradoException("No existe un usuario con el id: " + dto.usuarioId()
                                        )
                                    );
        
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);

        return prestamoMapper.toDto(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoResponseDTO findById(Long id) {
        return prestamoMapper.toDto(prestamoRepository.findById(id)
                                                                .orElseThrow( 
                                                                    () -> new RecursoNoEncontradoException("No existe un prestamo con el id: " + id) 
                                                                )
                                                            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDTO> findAll() {
        return prestamoRepository.findAll()
                                 .stream().map(prestamoMapper::toDto)
                                 .toList();
    }

    @Override
    @Transactional
    public PrestamoResponseDTO devolver(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                                              .orElseThrow(
                                                () -> new RecursoNoEncontradoException("El prestamo con id " + id + " no existe")
                                            );    

        if(prestamo.getFechaDevolucion() != null){
            throw new PrestamoYaDevueltoException("El prestamo con id " + id + " ya fue devuuelto el " + prestamo.getFechaDevolucion());
        }

        prestamo.setFechaDevolucion(LocalDateTime.now());    

        return prestamoMapper.toDto(prestamoRepository.save(prestamo));
    }
}
