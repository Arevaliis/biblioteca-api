package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.services.UsuarioService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<List<Usuario>>> findAll() {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuarios encontrados", usuarioService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<Usuario>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuario encontrado", usuarioService.findById(id)));
    }
    
    
    @PostMapping
    public ResponseEntity<RespuestaApi<Usuario>> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new RespuestaApi<>(true, "Usuario Creado", usuarioService.save(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Usuario>> update(@PathVariable Long id, @RequestBody Usuario usuario) {        
        return ResponseEntity.status(HttpStatus.RESET_CONTENT)
                             .body( new RespuestaApi<>(true, "Usuario Modificado", usuarioService.update(id, usuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String>> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body( new RespuestaApi<>(true, "Usuario eliminado", "Usuario con id " + id + " ha sido eliminado con exito"));
    }
}