package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.repositories.CategoriaRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    private CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    };

    
}
