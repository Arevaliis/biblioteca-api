package com.biblioteca.api.springboot_biblioteca_api.services;

import java.util.List;

import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;

public interface UsuarioService {

    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario update(Long id, Usuario usuario);
    void deleteById(Long id);
}