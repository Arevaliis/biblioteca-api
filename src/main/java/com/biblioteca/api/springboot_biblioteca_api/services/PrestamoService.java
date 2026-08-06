package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;

public interface PrestamoService {
    
    Prestamo save(Prestamo prestamo);
    Prestamo findById(Long id);
    List<Prestamo> findAll();
    Prestamo update(Long id);
}
