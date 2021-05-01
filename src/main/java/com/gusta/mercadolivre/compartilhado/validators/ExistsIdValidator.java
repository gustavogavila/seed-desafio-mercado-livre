package com.gusta.mercadolivre.compartilhado.validators;

import com.gusta.mercadolivre.compartilhado.annotations.ExistsId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {
    private Class<?> domainClass;
    private String fieldName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        domainClass = constraintAnnotation.domainClass();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (isNull(value)) {
            return true;
        }
        Query query = entityManager.createQuery("select 1 from " + domainClass.getName() + " where " + fieldName +
                "=:value");
        query.setParameter("value", value);
        return query.getResultStream().findAny().isPresent();
    }
}
