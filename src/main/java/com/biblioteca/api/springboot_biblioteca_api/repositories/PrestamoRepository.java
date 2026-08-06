package com.biblioteca.api.springboot_biblioteca_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{ }