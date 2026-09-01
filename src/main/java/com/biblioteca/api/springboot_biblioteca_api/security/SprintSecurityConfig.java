package com.biblioteca.api.springboot_biblioteca_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biblioteca.api.springboot_biblioteca_api.security.exception.AuthenticationEntryPointImpl;
import com.biblioteca.api.springboot_biblioteca_api.security.exception.CustomAccessDeniedHandlerImpl;
import com.biblioteca.api.springboot_biblioteca_api.security.filter.JwtAuthenticationFilter;


@Configuration
public class SprintSecurityConfig {

    private final CustomAccessDeniedHandlerImpl customAccessDeniedHandlerImpl;
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SprintSecurityConfig(
        AuthenticationEntryPointImpl authenticationEntryPointImpl, 
        CustomAccessDeniedHandlerImpl customAccessDeniedHandlerImpl,
        JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
        this.customAccessDeniedHandlerImpl = customAccessDeniedHandlerImpl;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity.authorizeHttpRequests(
                auth -> auth
                        // Login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        // Usuarios
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")

                        // Perfiles
                        .requestMatchers(HttpMethod.GET, "/perfiles").hasRole("ADMIN")

                        // Libros
                        .requestMatchers(HttpMethod.GET, "/libros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/libros/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/libros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/libros/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/libros/{id}").hasRole("ADMIN")

                        // Categorias
                        .requestMatchers(HttpMethod.GET, "/categorias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categorias/{id}").permitAll()

                        .requestMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categorias/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categorias/{id}").hasRole("ADMIN")

                        // Prestamos
                        .requestMatchers(HttpMethod.GET, "/prestamos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/prestamos/{id}").hasRole("ADMIN")

                        // Demas peticiones
                        .anyRequest().authenticated())
                        
                        // Captura las excepciones de Sprint Security
                        .exceptionHandling(ex -> 
                            ex.authenticationEntryPoint(authenticationEntryPointImpl)
                            .accessDeniedHandler(customAccessDeniedHandlerImpl)
                        )                      

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
