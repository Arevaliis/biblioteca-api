package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;
import com.biblioteca.api.springboot_biblioteca_api.repositories.PrestamoRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo findById(Long id) {
        return prestamoRepository.findById(id).orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo update(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));    
        prestamo.setFechaDevolucion(LocalDateTime.now());    
        return prestamoRepository.save(prestamo);
    }
}
