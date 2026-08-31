package com.biblioteca.api.springboot_biblioteca_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringbootBibliotecaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBibliotecaApiApplication.class, args);
	}

	@Bean
	ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
