package com.biblioteca.api.springboot_biblioteca_api.mappers;

import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.categoria.CategoriaUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Categoria;

@Component
public class CategoriaMapper {
    
    public Categoria toEntity(CategoriaCreateDTO dto){
        return new Categoria(dto.nombre());
    }

    public Categoria toEntity(CategoriaUpdateDTO dto){
        return new Categoria(dto.nombre());
    }

    public CategoriaResponseDTO toDto(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNombre());
    }
}
