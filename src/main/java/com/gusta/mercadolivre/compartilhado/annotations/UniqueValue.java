package com.gusta.mercadolivre.compartilhado.annotations;

import com.gusta.mercadolivre.compartilhado.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "UniqueValue";

    Class<?> domainClass();
    String fieldName();
}
