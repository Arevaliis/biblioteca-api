package com.biblioteca.api.springboot_biblioteca_api.dto.perfil;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PerfilUpdateDTO(
    
    @Pattern(regexp = "^(\\+34|0034|34)?[6789]\\d{8}$", message = "{perfil.telefono.pattern}")
    @NotBlank( message = "{perfil.telefono.notblank}")
    String telefono, 
    
    @Size(max= 150, message = "{perfil.direccion.size}")
    @NotBlank( message = "{perfil.direccion.notblank}")
    String direccion, 
    
    @NotNull(message = "{perfil.fechaNacimiento.notnull}")
    @Past(message = "{perfil.fechaNacimiento.past}")
    LocalDate fechaNacimiento

) {}