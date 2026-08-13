package com.biblioteca.api.springboot_biblioteca_api.dto.perfil;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PerfilCreateDTO(
    
    @Size(max= 15)
    @NotBlank
    String telefono, 
    
    @Size(max= 150)
    @NotBlank
    String direccion, 
    
    @NotNull
    @Past
    LocalDate fechaNacimiento, 
    
    @NotNull
    @Positive
    Long usuarioId

) {}
