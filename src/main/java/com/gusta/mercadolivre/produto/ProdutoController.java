package com.gusta.mercadolivre.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoRequest request) {
        Produto produto = request.toModel(entityManager);
        entityManager.persist(produto);
        return ResponseEntity.ok(produto);
    }
}
