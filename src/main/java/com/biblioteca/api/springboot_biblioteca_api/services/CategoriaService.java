package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;

public interface CategoriaService {
    
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Categoria fingById(Long id);
    Categoria update(Long id, Categoria categoria);
    void deleteById(Long id);
}
