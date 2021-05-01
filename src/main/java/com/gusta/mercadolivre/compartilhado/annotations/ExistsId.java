package com.gusta.mercadolivre.compartilhado.annotations;

import com.gusta.mercadolivre.compartilhado.validators.ExistsIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistsIdValidator.class})
public @interface ExistsId {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "ExistsId";

    Class<?> domainClass();
    String fieldName();
}
