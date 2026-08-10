package com.biblioteca.api.springboot_biblioteca_api.mappers;

import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.perfil.PerfilResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Perfil;

@Component
public class PerfilMapper {
    
    public Perfil toEntity(PerfilCreateDTO dto){
        return new Perfil(dto.telefono(), dto.direccion(), dto.fechaNacimiento());
    }

    public PerfilResponseDTO toDto(Perfil perfil){
        return new PerfilResponseDTO(perfil.getId() ,perfil.getTelefono(), perfil.getDireccion(), perfil.getFechaNacimiento(), perfil.getUsuario().getId());
    }

}
