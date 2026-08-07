package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.List;

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
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria fingById(Long id) {
        return categoriaRepository.findById(id).orElseThrow();
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        Categoria categoriaNueva = categoriaRepository.findById(id).orElseThrow();
        
        categoriaNueva.setNombre(categoria.getNombre());
        
        return categoriaRepository.save(categoriaNueva);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    };

    
}
