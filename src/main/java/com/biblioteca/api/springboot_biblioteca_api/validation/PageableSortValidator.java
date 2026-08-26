package com.biblioteca.api.springboot_biblioteca_api.validation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.biblioteca.api.springboot_biblioteca_api.exceptions.common.PropiedadNoPermitidaException;

@Component
public class PageableSortValidator {
    
    public void validateSort(List<String> listaBlanca, Pageable pageable){
        Optional<String> propiedadNoPermitida = pageable.getSort().stream()
                                                                    .map(Sort.Order::getProperty)
                                                                    .filter(p -> !listaBlanca.contains(p))
                                                                    .findFirst();

        if (propiedadNoPermitida.isPresent()) {
            throw new PropiedadNoPermitidaException(propiedadNoPermitida.get());
        }
    }
}
