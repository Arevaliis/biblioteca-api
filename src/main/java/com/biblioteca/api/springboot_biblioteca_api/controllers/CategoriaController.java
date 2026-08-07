package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.services.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    private CategoriaServiceImpl categoriaServiceImpl;

    public CategoriaController(CategoriaServiceImpl categoriaServiceImpl) {
        this.categoriaServiceImpl = categoriaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<Categoria>> save(@RequestBody Categoria categoria) {        
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Categoria Registrada", categoriaServiceImpl.save(categoria)));
    }
}
