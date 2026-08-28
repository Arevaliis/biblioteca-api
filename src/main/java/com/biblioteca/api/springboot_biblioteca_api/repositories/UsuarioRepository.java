package com.biblioteca.api.springboot_biblioteca_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Usuario> findByEmail(String email);

}