package com.gusta.mercadolivre.produto;

import com.gusta.mercadolivre.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.function.Function;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    private Instant criadoEm;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
                   Function<Produto, Set<CaracteristicaProduto>> caracteristicasFn, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicasFn.apply(this);
        this.categoria = categoria;
        this.criadoEm = Instant.now();
    }
}
