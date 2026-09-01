package com.biblioteca.api.springboot_biblioteca_api.security.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import static com.biblioteca.api.springboot_biblioteca_api.security.TokenJwtConfig.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
 
    public String generarJwt(Authentication authentication) {
        String rol = authentication.getAuthorities().stream()
                                                    .map(GrantedAuthority::getAuthority)
                                                    .findFirst()
                                                    .orElseThrow();

        return Jwts.builder()
                .subject(authentication.getName())
                .signWith(SECRET_KEY)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
    }

    public boolean validarJwt(String jwt) {
        try{
            Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jwt);
            return true;

        }catch (Exception e){ return false; }
    }

    public Claims extraerClaims(String jwt) {
        return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();       
    }

    public String extraerUsername(String jwt) {
        return extraerClaims(jwt).getSubject();
    }

    public String extraerRol(String jwt) {
        return extraerClaims(jwt).get("rol", String.class);
    }

    public Date extraerFechaExpiracion(String jwt) {
        return extraerClaims(jwt).getExpiration();
    }

    public boolean isTokenExpired(String jwt) {
        return extraerFechaExpiracion(jwt).before(new Date());
    }

}
