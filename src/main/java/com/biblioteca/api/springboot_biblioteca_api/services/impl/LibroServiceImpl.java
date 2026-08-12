package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;
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
        
        List<Long> categoriasRecibidas  = dto.categoriasId();
        Libro libro = libroMapper.toEntity(dto);

        categoriasRecibidas.stream()
                           .forEach( 
                                idCat ->  libro.addCategoria(
                                    categoriaRepository.findById(idCat).orElseThrow()
                                )
                            );

        return libroMapper.toDto(libroRepository.save(libro));

    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponseDTO findById(Long id) {
        return libroMapper.toDto(libroRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroResponseDTO> findAll() {
        return libroRepository.findAll().stream().map(libroMapper::toDto).toList();
    }

    @Override
    @Transactional
    public LibroResponseDTO update(Long id, LibroUpdateDTO dto) {
        Libro libroNuevo = libroRepository.findById(id).orElseThrow();

        libroNuevo.setAutor(dto.autor());
        libroNuevo.setTitulo(dto.titulo());
        libroNuevo.setCategorias(new ArrayList<>());

        dto.categoriasId().stream()
                           .forEach( 
                                idCat ->  libroNuevo.addCategoria(
                                    categoriaRepository.findById(idCat).orElseThrow()
                                )
                            );

        return libroMapper.toDto(libroRepository.save(libroNuevo));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }
}
