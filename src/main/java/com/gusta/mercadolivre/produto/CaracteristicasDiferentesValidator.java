package com.gusta.mercadolivre.produto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 5
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
        List<CaracteristicaProdutoRequest> caracteristicas = produtoRequest.getCaracteristicas();
        Set<CaracteristicaProdutoRequest> set = new HashSet<>();
        // 1
        for (CaracteristicaProdutoRequest c : caracteristicas) {
            // 1
            if (!set.add(c)) {
                errors.rejectValue("caracteristicas", "CaracteristicasDiferentesValidator",
                        "A característica " + c.getNome() + " está repetida!");
            }
        }
    }
}
