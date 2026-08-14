package com.biblioteca.api.springboot_biblioteca_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.api.springboot_biblioteca_api.entities.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    boolean existsByTelefono(String telefono);

    boolean existsByTelefonoAndIdNot(String telefono, Long id);
 }