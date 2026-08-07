package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;
import com.biblioteca.api.springboot_biblioteca_api.services.LibroService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/libros")
public class LibroController {
    
    private LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<Libro>> save(@RequestBody Libro libro) {        
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libro Registrado", libroService.save(libro)));
    }
    
    @GetMapping
    public ResponseEntity<RespuestaApi<List<Libro>>> findAll() {        
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libros Encontrados", libroService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<Libro>> findById(@PathVariable Long id) {        
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libro Encontrado", libroService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Libro>> update(@PathVariable Long id, @RequestBody Libro libro) {        
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libro Actualizado", libroService.update(id, libro)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String   >> delete(@PathVariable Long id) {        
        libroService.deleteById(id);

        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libro Eliminado", "Libro con id " + id + " ha sido eliminada con exito"));
    }

}
