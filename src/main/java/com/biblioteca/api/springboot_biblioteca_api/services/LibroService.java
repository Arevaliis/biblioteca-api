package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;

public interface LibroService {
    
    Libro save(Libro libro);
    Libro findById(Long id);
    List<Libro> findAll();
    Libro update(Long id, Libro libro);
    void deleteById(Long id);
}
