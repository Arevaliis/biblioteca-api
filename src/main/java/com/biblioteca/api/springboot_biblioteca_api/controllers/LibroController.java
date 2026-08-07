package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;
import com.biblioteca.api.springboot_biblioteca_api.services.LibroService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    

}
