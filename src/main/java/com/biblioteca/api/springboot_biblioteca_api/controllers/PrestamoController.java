package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.prestamo.PrestamoResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.PrestamoService;
import com.biblioteca.api.springboot_biblioteca_api.validation.PageableSortValidator;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

    private final PrestamoService prestamoService;
    private final PageableSortValidator pageableSortValidator;


    public PrestamoController(PrestamoService prestamoService, PageableSortValidator pageableSortValidator) {
        this.prestamoService = prestamoService;        
        this.pageableSortValidator = pageableSortValidator;

    }

    @GetMapping
    public ResponseEntity<RespuestaApi<PageResponseDTO<PrestamoResponseDTO>>> findAll(
        @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable
    ) {

        List<String> listaBlanca = List.of("id", "usuario");
        pageableSortValidator.validateSort(listaBlanca, pageable);
        
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamos encontrados", prestamoService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamo encontrado", prestamoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> save(@Valid @RequestBody PrestamoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Prestamo Registrado", prestamoService.save(dto)));
    }
    
    @PutMapping("/{id}/devolucion")
    public ResponseEntity<RespuestaApi<PrestamoResponseDTO>> update(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Prestamo Devuelto", prestamoService.devolver(id)));
    }
}
