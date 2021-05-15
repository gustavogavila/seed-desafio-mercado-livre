package com.gusta.mercadolivre.imagemproduto;

import com.gusta.mercadolivre.compartilhado.annotations.ExistsId;
import com.gusta.mercadolivre.produto.Produto;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Objects.nonNull;

public class ImagemProdutoRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    @NotEmpty
    @Size(min = 1)
    private List<String> imagens;

    public ImagemProdutoRequest(Long produtoId, List<String> imagens) {
        this.produtoId = produtoId;
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return "ImagemProdutoRequest{" +
                "produtoId=" + produtoId +
                ", imagens=" + imagens +
                '}';
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public ImagemProduto toModel(EntityManager entityManager) {
        Produto produto = entityManager.find(Produto.class, produtoId);
        Assert.state(nonNull(produto), "O produto informado não existe. Id: " + produtoId);
        return new ImagemProduto("caminhoImagem", produto);
    }
}
