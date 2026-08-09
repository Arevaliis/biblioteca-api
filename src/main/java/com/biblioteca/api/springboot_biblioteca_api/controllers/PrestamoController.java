package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.entities.Prestamo;
import com.biblioteca.api.springboot_biblioteca_api.services.PrestamoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<List<Prestamo>>> findAll() {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Prestamos encontrados", prestamoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<Prestamo>> findById(Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Prestamo encontrado", prestamoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<Prestamo>> save(@RequestBody Prestamo prestamo) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamo Registrado", prestamoService.save(prestamo)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Prestamo>> putMethodName(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body(new RespuestaApi<>(true, "Prestamo Devuelto", prestamoService.update(id)));
    }
}
