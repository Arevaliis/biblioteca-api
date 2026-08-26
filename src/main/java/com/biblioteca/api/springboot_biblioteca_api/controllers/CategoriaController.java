package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.PageResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.CategoriaService;
import com.biblioteca.api.springboot_biblioteca_api.services.impl.CategoriaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    private final CategoriaService categoriaServiceImpl;

    public CategoriaController(CategoriaServiceImpl categoriaServiceImpl) {
        this.categoriaServiceImpl = categoriaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<CategoriaResponseDTO>> save(@Valid @RequestBody CategoriaCreateDTO dto) {        
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body( new RespuestaApi<>(true, "Categoria Registrada", categoriaServiceImpl.save(dto)));
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<PageResponseDTO<CategoriaResponseDTO>>> findAll(
        @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Categorias encontradas", categoriaServiceImpl.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<CategoriaResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Categoria encontrada", categoriaServiceImpl.findById(id)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<CategoriaResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody  CategoriaUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Categoria Modificada", categoriaServiceImpl.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String>> deleteById(@PathVariable Long id) {
        categoriaServiceImpl.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Categoria eliminada", "Categoria con id " + id + " ha sido eliminada con exito"));
    }
}
