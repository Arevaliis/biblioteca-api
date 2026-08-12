package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.mappers.CategoriaMapper;
import com.biblioteca.api.springboot_biblioteca_api.repositories.CategoriaRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO save(CategoriaCreateDTO dto) {
        return categoriaMapper.toDto(categoriaRepository.save(categoriaMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(Long id) {
        return categoriaMapper.toDto(categoriaRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaUpdateDTO dto) {
        Categoria categoriaActulizada = categoriaRepository.findById(id).orElseThrow();
        categoriaActulizada.setNombre(dto.nombre());
        
        return categoriaMapper.toDto(categoriaRepository.save(categoriaActulizada));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    };
}