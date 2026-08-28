package com.biblioteca.api.springboot_biblioteca_api.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

    @Email(message = "{login.email.email}")
    @Size(max = 50, message = "{login.email.size}")
    @NotBlank(message = "{login.email.notblank}")
    String email,

    @NotBlank(message = "{login.password.notblank}")
    @Size(min = 8, max = 24, message = "{login.password.size}")
    String password

) {}