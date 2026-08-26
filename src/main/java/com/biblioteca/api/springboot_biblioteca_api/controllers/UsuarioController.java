package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.UsuarioService;
import com.biblioteca.api.springboot_biblioteca_api.validation.PageableSortValidator;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PageableSortValidator pageableSortValidator;

    public UsuarioController(UsuarioService usuarioService, PageableSortValidator pageableSortValidator ) {
        this.usuarioService = usuarioService;
        this.pageableSortValidator = pageableSortValidator;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<PageResponseDTO<UsuarioResponseDTO>>> findAll(
        @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable
    ) {

        List<String> listaBlanca = List.of("id", "nombre");
        pageableSortValidator.validateSort(listaBlanca, pageable);

        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuarios encontrados", usuarioService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<UsuarioResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuario encontrado", usuarioService.findById(id)));
    }
    
    
    @PostMapping
    public ResponseEntity<RespuestaApi<UsuarioResponseDTO>> save(@Valid @RequestBody UsuarioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Usuario Creado", usuarioService.save(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<UsuarioUpdateResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Usuario Modificado", usuarioService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String>> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body( new RespuestaApi<>(true, "Usuario eliminado", "Usuario con id " + id + " ha sido eliminado con exito"));
    }
}