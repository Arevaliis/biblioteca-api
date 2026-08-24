package com.biblioteca.api.springboot_biblioteca_api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
    
    @NotBlank( message = "{usuario.nombre.notblank}")
    @Size(min= 3, max=20, message = "{usuario.nombre.size}")
    String nombre, 
    
    @Email( message = "{usuario.email.email}")
    @Size(max=50, message = "{usuario.email.size}" )
    @NotBlank( message = "{usuario.email.notblank}")
    String email

) {}