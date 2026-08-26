package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoDuplicadoException;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
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
        
        if (categoriaRepository.existsByNombre(dto.nombre())){
            throw new RecursoDuplicadoException("La categoria con nombre " + dto.nombre() + " ya esta siendo usado");
        }

        return categoriaMapper.toDto(
            categoriaRepository.save(
                            categoriaMapper.toEntity(dto)
                        )
                    );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<CategoriaResponseDTO> findAll(Pageable pageable) {
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        Page<CategoriaResponseDTO> categoriasDTO = categorias.map(categoriaMapper::toDto);

        return new PageResponseDTO<>(
                        categoriasDTO.getContent(),
                        categoriasDTO.getNumber() + 1,
                        categoriasDTO.getSize(),
                        categoriasDTO.getTotalElements(),
                        categoriasDTO.getTotalPages(),
                        categoriasDTO.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(Long id) {
        return categoriaMapper.toDto(
                    categoriaRepository.findById(id)
                                        .orElseThrow(
                                            () -> new RecursoNoEncontradoException("La categoria con id " + id + " no existe")
                                        )
                                    );
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaUpdateDTO dto) {
        Categoria categoriaActulizada = categoriaRepository.findById(id)
                                                           .orElseThrow(
                                                                () -> new RecursoNoEncontradoException("La categoria con id " + id + " no existe")
                                                            );

        if (categoriaRepository.existsByNombreAndIdNot(dto.nombre(), id)){
            throw new RecursoDuplicadoException("La categoria con nombre " + dto.nombre() + " ya esta siendo usado");
        }

        categoriaActulizada.setNombre(dto.nombre());
        
        return categoriaMapper.toDto(categoriaRepository.save(categoriaActulizada));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.findById(id)
                           .orElseThrow(
                                () -> new RecursoNoEncontradoException("La categoria con id " + id + " no existe")
                            );

        categoriaRepository.deleteById(id);
    };
}