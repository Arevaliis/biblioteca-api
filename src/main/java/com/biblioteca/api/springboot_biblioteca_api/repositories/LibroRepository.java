package com.biblioteca.api.springboot_biblioteca_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{ }
