package com.biblioteca.api.springboot_biblioteca_api.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.PerfilService;
import com.biblioteca.api.springboot_biblioteca_api.validation.PageableSortValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
    
    private final PerfilService perfilService;
    private final PageableSortValidator pageableSortValidator;

    public PerfilController(PerfilService perfilService, PageableSortValidator pageableSortValidator) {
        this.perfilService = perfilService;
        this.pageableSortValidator = pageableSortValidator;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<PageResponseDTO<PerfilResponseDTO>>> findAll(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable
        ) { 
        
        List<String> listaBlanca = List.of("id", "fechaNacimiento", "direccion");
        pageableSortValidator.validateSort(listaBlanca, pageable);

        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Perfiles encontrados", perfilService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Perfil encontrado", perfilService.findById(id)));
    }
    
    
    @PostMapping
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> save(@Valid @RequestBody PerfilCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Perfil Creado", perfilService.save(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody PerfilUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Perfil Modificado", perfilService.update(id, dto)));
    }
}
