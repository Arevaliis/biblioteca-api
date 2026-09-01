package com.biblioteca.api.springboot_biblioteca_api.security.exception;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandlerImpl implements AccessDeniedHandler  {

    private final ObjectMapper objectMapper; 

    public CustomAccessDeniedHandlerImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(
        HttpServletRequest request, 
        HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {

        HttpStatus status = HttpStatus.FORBIDDEN;

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setInstance(URI.create("https://api.biblioteca.com/errors/permisos"));
        problemDetail.setDetail("No tiene los permisos necesarios para acceder a este recurso");
        problemDetail.setTitle("Acceso denegado");

        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(objectMapper.writeValueAsString(problemDetail));
    }
    
}
