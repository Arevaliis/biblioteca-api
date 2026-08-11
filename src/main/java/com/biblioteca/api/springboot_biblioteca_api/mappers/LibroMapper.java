package com.biblioteca.api.springboot_biblioteca_api.mappers;

import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.libro.LibroUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;
import com.biblioteca.api.springboot_biblioteca_api.entities.Libro;

@Component
public class LibroMapper {
    
    public Libro toEntity(LibroCreateDTO dto){
        return new Libro(dto.titulo(), dto.autor() );
    }

    public Libro toEntity(LibroUpdateDTO dto){
        return new Libro(dto.titulo(), dto.autor() );
    }

    public LibroResponseDTO toDto(Libro libro){
        return new LibroResponseDTO(
            libro.getId(), 
            libro.getTitulo(), 
            libro.getAutor(),
            libro.getCategorias().stream().map(Categoria::getNombre).toList()       
        );
    }
}
