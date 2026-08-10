package com.biblioteca.api.springboot_biblioteca_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.PerfilService;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
    
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<List<PerfilResponseDTO>>> findAll() {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Perfiles encontrados", perfilService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Perfil encontrado", perfilService.findById(id)));
    }
    
    
    @PostMapping
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> save(@RequestBody PerfilCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Perfil Creado", perfilService.save(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<PerfilResponseDTO>> update(@PathVariable Long id, @RequestBody PerfilUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Perfil Modificado", perfilService.update(id, dto)));
    }
}
