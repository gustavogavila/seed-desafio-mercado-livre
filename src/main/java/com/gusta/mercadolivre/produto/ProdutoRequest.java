package com.gusta.mercadolivre.produto;

import com.gusta.mercadolivre.categoria.Categoria;
import com.gusta.mercadolivre.compartilhado.annotations.ExistsId;
import com.gusta.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<CaracteristicaProdutoRequest> caracteristicas;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
                          List<CaracteristicaProdutoRequest> caracteristicas, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public List<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager entityManager, Long usuarioLogadoId) {
        Categoria categoria = entityManager.find(Categoria.class, categoriaId);
        Assert.state(nonNull(categoria), "Categoria informada não encontrada. Id = " + categoriaId);
        Usuario usuario = entityManager.find(Usuario.class, usuarioLogadoId);
        Assert.state(nonNull(usuario), "Usuário logado deveria existir nesse ponto. Id: = " + usuarioLogadoId);
        Function<Produto, Set<CaracteristicaProduto>> caracteristicasFn = gerarCaracteristicas(caracteristicas);
        return new Produto(nome, valor, quantidadeDisponivel, descricao, caracteristicasFn, categoria, usuario);
    }

    private Function<Produto, Set<CaracteristicaProduto>> gerarCaracteristicas(
            List<CaracteristicaProdutoRequest> caracteristicas) {
        return (produto -> caracteristicas.stream().map(
                c -> c.toModel(produto)).collect(Collectors.toSet()));
    }

    public String buscarCaracteristicasIguais() {
        List<String> nomesIguais = new ArrayList<>();
        Set<String> nomesDiferentes = new HashSet<>();
        for (CaracteristicaProdutoRequest c : caracteristicas) {
            if (!nomesDiferentes.add(c.getNome())) {
                nomesIguais.add(c.getNome());
            }
        }
        return nomesIguais.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}

