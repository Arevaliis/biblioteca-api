package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.login.LoginRequestDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public Authentication loginUsuario(@RequestBody LoginRequestDTO dto) {
        String email = dto.email();
        String password = dto.password();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
                                                                                
        Authentication authentication =  authenticationManager.authenticate(token);

        return authentication;
    }

}