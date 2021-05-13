package com.gusta.mercadolivre.produto;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

import static java.util.Objects.nonNull;

// 3
@Component
public class CaracteristicasDiferentesValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ProdutoRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // 1
        if (errors.hasErrors()) {
            return;
        }
        // 1
        ProdutoRequest produtoRequest = (ProdutoRequest) o;
        // 1
        String nomesIguais = produtoRequest.buscarCaracteristicasIguais();
        if(StringUtils.hasText(nomesIguais)) {
            errors.rejectValue("caracteristicas", null,
                    "Características repetidas: " + nomesIguais);
        }
    }
}
