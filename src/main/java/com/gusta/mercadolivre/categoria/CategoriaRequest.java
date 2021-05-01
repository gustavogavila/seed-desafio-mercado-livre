package com.gusta.mercadolivre.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gusta.mercadolivre.compartilhado.annotations.ExistsId;
import com.gusta.mercadolivre.compartilhado.annotations.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import static java.util.Objects.nonNull;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaMaeId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaRequest(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager em) {
        Categoria categoria = new Categoria(nome);
        if (nonNull(categoriaMaeId)) {
            Categoria categoriaExistente = em.find(Categoria.class, categoriaMaeId);
            Assert.state(nonNull(categoriaExistente), "A categoria informada não existe");
            categoria.setCategoriaMae(categoriaExistente);
        }
        return categoria;
    }
}
