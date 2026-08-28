package com.biblioteca.api.springboot_biblioteca_api.mappers;

import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;

@Component
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioCreateDTO dto){
        return new Usuario( dto.nombre(), dto.email(), dto.password());
    }

    public UsuarioResponseDTO toDTO(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getId(),usuario.getNombre(), usuario.getEmail(), usuario.getFechaRegistro(), usuario.getRol());
    }

    public UsuarioUpdateResponseDTO toUpdateDTO(Usuario usuario){
        return new UsuarioUpdateResponseDTO(usuario.getId(),usuario.getNombre(), usuario.getEmail(), usuario.getFechaRegistro(), usuario.getFechaActualizacion(), usuario.getRol());
    }
}
