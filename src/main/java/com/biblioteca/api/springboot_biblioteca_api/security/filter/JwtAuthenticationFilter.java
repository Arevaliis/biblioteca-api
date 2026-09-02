package com.biblioteca.api.springboot_biblioteca_api.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.biblioteca.api.springboot_biblioteca_api.security.service.JwtService;
import static com.biblioteca.api.springboot_biblioteca_api.security.TokenJwtConfig.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain 

    ) throws ServletException, IOException {

        // Obtiene la cabecera Authorization
        String header = request.getHeader(HEADER_AUTHORIZATION);

        // Si no hay token, continúa la petición. Sirve para endpoint publicos
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el JWT eliminando el prefijo "Bearer "
        String jwt = header.substring(PREFIX_TOKEN.length());

        // Comprueba que el JWT tenga una estructura válida o si el token ha caducado
        if (!jwtService.validarJwt(jwt) || jwtService.isTokenExpired(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extraerUsername(jwt);

        // Convierte los roles en autoridades de Spring Security
        Collection<? extends GrantedAuthority> roles = 
            List.of(jwtService.extraerRol(jwt)).stream()
                                                   .map(SimpleGrantedAuthority::new)
                                                   .collect(Collectors.toList());

        // Crea la autenticación del usuario
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                username,
                null,
                roles
            );

        // Guarda la autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Continúa con la petición
        filterChain.doFilter(request, response);
    }
    
}