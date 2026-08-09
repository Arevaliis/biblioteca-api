package com.biblioteca.api.springboot_biblioteca_api.controllers;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<RespuestaApi<List<Categoria>>> findAll() {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Categorias encontradas", categoriaServiceImpl.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<Categoria>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Categoria encontrada", categoriaServiceImpl.fingById(id)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Categoria>> update(@PathVariable Long id, @RequestBody  Categoria categoria) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Categoria Modificada", categoriaServiceImpl.update(id, categoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String>> deleteById(@PathVariable Long id) {
        categoriaServiceImpl.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body( new RespuestaApi<>(true, "Categoria eliminada", "Categoria con id " + id + " ha sido eliminada con exito"));
    }
}
