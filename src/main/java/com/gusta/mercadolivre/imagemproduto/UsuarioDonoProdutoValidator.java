package com.gusta.mercadolivre.imagemproduto;

import com.gusta.mercadolivre.produto.Produto;
import com.gusta.mercadolivre.seguranca.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

import static java.util.Objects.nonNull;

@Component
public class UsuarioDonoProdutoValidator implements Validator {

    private final EntityManager em;
    private final UsuarioLogadoService usuarioLogadoService;

    @Autowired
    public UsuarioDonoProdutoValidator(EntityManager em, UsuarioLogadoService usuarioLogadoService) {
        this.em = em;
        this.usuarioLogadoService = usuarioLogadoService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ImagemProdutoRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ImagemProdutoRequest imagemProdutoRequest = (ImagemProdutoRequest) o;
        Produto produto = em.find(Produto.class, imagemProdutoRequest.getProdutoId());
        Assert.state(nonNull(produto), "O produto informado não existe.");
        if (!produto.pertenceAo(usuarioLogadoService.getUsuarioLogado().getUsername())) {
            errors.rejectValue("produtoId", null, "O produto informado não pertence a você.");
        }
    }
}
