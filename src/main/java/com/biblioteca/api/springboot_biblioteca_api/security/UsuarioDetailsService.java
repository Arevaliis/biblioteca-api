package com.biblioteca.api.springboot_biblioteca_api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        
        Usuario usuario = usuarioRepository.findByEmail(email)
                                           .orElseThrow( 
                                                () -> new UsernameNotFoundException("Usuario no encontrado") 
                                            );

        return new UsuarioDetails(usuario);
    }
    
}
