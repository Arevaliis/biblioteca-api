package com.biblioteca.api.springboot_biblioteca_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.springboot_biblioteca_api.dto.RespuestaApi;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioCreateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateDTO;
import com.biblioteca.api.springboot_biblioteca_api.dto.usuario.UsuarioUpdateResponseDTO;
import com.biblioteca.api.springboot_biblioteca_api.entities.Usuario;
import com.biblioteca.api.springboot_biblioteca_api.services.UsuarioService;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<RespuestaApi<List<UsuarioResponseDTO>>> findAll() {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuarios encontrados", usuarioService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaApi<UsuarioResponseDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Usuario encontrado", usuarioService.findById(id)));
    }
    
    
    @PostMapping
    public ResponseEntity<RespuestaApi<UsuarioResponseDTO>> save(@RequestBody UsuarioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new RespuestaApi<>(true, "Usuario Creado", usuarioService.save(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<UsuarioUpdateResponseDTO>> update(@PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {        
        return ResponseEntity.status(HttpStatus.RESET_CONTENT)
                             .body( new RespuestaApi<>(true, "Usuario Modificado", usuarioService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaApi<String>> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .body( new RespuestaApi<>(true, "Usuario eliminado", "Usuario con id " + id + " ha sido eliminado con exito"));
    }
}