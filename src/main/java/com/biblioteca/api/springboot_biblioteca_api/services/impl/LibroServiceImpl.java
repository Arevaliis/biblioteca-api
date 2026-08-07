package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;
import com.biblioteca.api.springboot_biblioteca_api.repositories.CategoriaRepository;
import com.biblioteca.api.springboot_biblioteca_api.repositories.LibroRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {
    
    private LibroRepository libroRepository;
    private CategoriaRepository categoriaRepository;

    public LibroServiceImpl(LibroRepository libroRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Libro save(Libro libro) {
        
        List<Categoria> categoriasRecibidas  = libro.getCategorias();
        List<Categoria> categorias = new ArrayList<>();

        categoriasRecibidas .stream()
                            .forEach( 
                                cat -> categorias.add(categoriaRepository.findById(cat.getId()).orElseThrow())
                            );

        libro.setCategorias(categorias);

        return libroRepository.save(libro);

    }    
}
