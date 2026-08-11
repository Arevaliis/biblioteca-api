package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.services.LibroService;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<LibroResponseDTO>> save(@RequestBody LibroCreateDTO dto) {        
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Libro Registrado", libroService.save(dto)));
    }
    
    @GetMapping
    public ResponseEntity<RespuestaApi<List<LibroResponseDTO>>> findAll() {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Libros Encontrados", libroService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<LibroResponseDTO>> findById(@PathVariable Long id) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Libro Encontrado", libroService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<LibroResponseDTO>> update(@PathVariable Long id, @RequestBody LibroUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Libro Actualizado", libroService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String   >> delete(@PathVariable Long id) {        
        libroService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body( new RespuestaApi<>(true, "Libro Eliminado", "Libro con id " + id + " ha sido eliminada con exito"));
    }

}
