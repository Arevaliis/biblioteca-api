package com.biblioteca.api.springboot_biblioteca_api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
    
    @NotBlank
    @Size(min= 3, max=20 )
    String nombre, 
    
    @Email
    @Size(max=50 )
    @NotBlank
    String email

) {}