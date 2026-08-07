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

    @Override
    public Libro findById(Long id) {
        return libroRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro update(Long id, Libro libro) {
        Libro libroNuevo = libroRepository.findById(id).orElseThrow();

        libro.setAutor(libro.getAutor());
        libroNuevo.setTitulo(libro.getTitulo());

        return libroRepository.save(libro);
    }

    @Override
    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }    
}
