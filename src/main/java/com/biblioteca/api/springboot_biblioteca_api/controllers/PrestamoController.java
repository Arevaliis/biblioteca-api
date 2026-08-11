package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;
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
    public ResponseEntity<RespuestaApi<List<PrestamoResponseDTO>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamos encontrados", prestamoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamo encontrado", prestamoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> save(@RequestBody PrestamoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Prestamo Registrado", prestamoService.save(dto)));
    }
    
    @PutMapping("/{id}/devolucion")
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> update(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamo Devuelto", prestamoService.devolver(id)));
    }
}
