package com.gusta.mercadolivre.compartilhado.validators;

import com.gusta.mercadolivre.compartilhado.annotations.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private Class<?> domainClass;
    private String domainAttribute;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        domainClass = constraintAnnotation.domainClass();
        domainAttribute = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + domainClass.getName() + " where " +
                domainAttribute + "=:value");
        query.setParameter("value", value);
        List list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + domainClass.getName() +
                " com o atributo " + domainAttribute + " = " + value);
        return list.isEmpty();
    }
}
