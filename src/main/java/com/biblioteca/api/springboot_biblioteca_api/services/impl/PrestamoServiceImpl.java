package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import com.biblioteca.api.springboot_biblioteca_api.mappers.PrestamoMapper;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.repositories.PrestamoRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoMapper prestamoMapper;
    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,  UsuarioRepository usuarioRepository, PrestamoMapper prestamoMapper) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.prestamoMapper = prestamoMapper;
    }

    @Override
    public PrestamoResponseDTO save(PrestamoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow();
        
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);

        return prestamoMapper.toDto(prestamoRepository.save(prestamo));
    }

    @Override
    public PrestamoResponseDTO findById(Long id) {
        return prestamoMapper.toDto(prestamoRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PrestamoResponseDTO> findAll() {
        return prestamoRepository.findAll().stream().map(prestamoMapper::toDto).toList();
    }

    @Override
    public PrestamoResponseDTO devolver(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow(() -> new RuntimeException());    
        prestamo.setFechaDevolucion(LocalDateTime.now());    

        return prestamoMapper.toDto(prestamoRepository.save(prestamo));
    }
}
