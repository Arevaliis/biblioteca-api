package com.biblioteca.api.springboot_biblioteca_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { 

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);
}
