package com.biblioteca.api.springboot_biblioteca_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.repositories.UsuarioRepository;
import com.biblioteca.api.springboot_biblioteca_api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("No hay ningun usuario con el id: " + id)); 
                                // Crearemos excepcion personalizada
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Usuario oldUsuario = usuarioRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("No hay ningun usuario con el id: " + id)); 

        oldUsuario.setNombre(usuario.getNombre());
        oldUsuario.setEmail(usuario.getEmail());
        usuario.getPerfil().setId(id);

        oldUsuario.setPerfil(usuario.getPerfil());
        

        return usuarioRepository.save(oldUsuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
