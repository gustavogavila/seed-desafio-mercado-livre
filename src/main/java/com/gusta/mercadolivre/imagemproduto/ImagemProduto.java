package com.gusta.mercadolivre.imagemproduto;

import com.gusta.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String caminhoImagem;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String caminhoImagem, Produto produto) {
        this.caminhoImagem = caminhoImagem;
        this.produto = produto;
    }
}
