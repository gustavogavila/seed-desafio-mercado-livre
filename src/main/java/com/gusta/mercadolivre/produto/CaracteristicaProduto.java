package com.gusta.mercadolivre.produto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String descricao, Produto produto) {
        this.descricao = descricao;
        this.produto = produto;
    }
}
