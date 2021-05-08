package com.gusta.mercadolivre.produto;

import com.gusta.mercadolivre.categoria.Categoria;
import com.gusta.mercadolivre.compartilhado.annotations.ExistsId;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidadeDisponivel;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotEmpty
    @Size(min = 3, message = "Precisa haver, no mínimo, 3 características")
    private Set<String> caracteristicas;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
                          Set<String> caracteristicas, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", categoriaId=" + categoriaId +
                '}';
    }

    public Produto toModel(EntityManager entityManager) {
        Categoria categoria = entityManager.find(Categoria.class, categoriaId);
        Assert.state(nonNull(categoria), "Categoria informada não encontrada. Id = " + categoriaId);
        Function<Produto, Set<CaracteristicaProduto>> caracteristicasFn = gerarCaracteristicas(caracteristicas);
        return new Produto(nome, valor, quantidadeDisponivel, descricao, caracteristicasFn, categoria);
    }

    private Function<Produto, Set<CaracteristicaProduto>> gerarCaracteristicas(Set<String> caracteristicas) {
        return (produto -> caracteristicas.stream().map(
                c -> new CaracteristicaProduto(c, produto)).collect(Collectors.toSet()));
    }
}

