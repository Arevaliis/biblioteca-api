package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;
import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.RecursoNoEncontradoException;
import com.biblioteca.api.springboot_biblioteca_api.mappers.LibroMapper;
import com.biblioteca.api.springboot_biblioteca_api.repositories.CategoriaRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.LibroRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {
    
    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;
    private final LibroMapper libroMapper;

    public LibroServiceImpl(LibroRepository libroRepository, CategoriaRepository categoriaRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
        this.libroMapper = libroMapper;
    }

    @Override
    @Transactional
    public LibroResponseDTO save(LibroCreateDTO dto) {
        
        List<Long> categoriasRecibidas  = dto.categoriasIds();
        Libro libro = libroMapper.toEntity(dto);

        categoriasRecibidas.stream()
                           .forEach( 
                                idCat ->  libro.addCategoria(
                                    categoriaRepository.findById(idCat)
                                                                  .orElseThrow(
                                                                        () -> new RecursoNoEncontradoException(
                                                                            "La categoria con id " + idCat + " no existe"
                                                                        )
                                    )
                                )
                            );

        return libroMapper.toDto(libroRepository.save(libro));

    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponseDTO findById(Long id) {
        return libroMapper.toDto(libroRepository.findById(id)
                                                       .orElseThrow(
                                                                () -> new RecursoNoEncontradoException("El libro con id " + id + " no existe")
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<LibroResponseDTO> findAll(Pageable pageable) {

        Page<Libro> libros = libroRepository.findAll(pageable);
        Page<LibroResponseDTO> librosDTO = libros.map(libroMapper::toDto);

        return new PageResponseDTO<>(
                        librosDTO.getContent(),
                        librosDTO.getNumber() + 1,
                        librosDTO.getSize(),
                        librosDTO.getTotalElements(),
                        librosDTO.getTotalPages(),
                        librosDTO.isLast()
        );
    }

    @Override
    @Transactional
    public LibroResponseDTO update(Long id, LibroUpdateDTO dto) {
        Libro libroNuevo = libroRepository.findById(id)
                                          .orElseThrow(
                                                () -> new RecursoNoEncontradoException("El libro con id " + id + " no existe")
                                            );

        libroNuevo.setAutor(dto.autor());
        libroNuevo.setTitulo(dto.titulo());
        libroNuevo.setCategorias(new ArrayList<>());

        dto.categoriasIds().stream()
                           .forEach( 
                                idCat ->  libroNuevo.addCategoria(
                                    categoriaRepository.findById(idCat)
                                                                  .orElseThrow(
                                                                    () -> new RecursoNoEncontradoException("La categoria con id " + idCat + " no existe")
                                                                  )
                                                                )
                                );

        return libroMapper.toDto(libroRepository.save(libroNuevo));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        libroRepository.findById(id)
                        .orElseThrow(
                            () -> new RecursoNoEncontradoException("El libro con id " + id + " no existe")
                        );
        
        libroRepository.deleteById(id);
    }
}
