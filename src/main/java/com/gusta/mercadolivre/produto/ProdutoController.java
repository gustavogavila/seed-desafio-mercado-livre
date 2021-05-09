package com.gusta.mercadolivre.produto;

import com.gusta.mercadolivre.seguranca.MyUserDetails;
import com.gusta.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<Void> criar(@Valid @RequestBody ProdutoRequest request,
                                         @AuthenticationPrincipal MyUserDetails usuarioLogado) {
        Produto produto = request.toModel(entityManager, usuarioLogado.getId());
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }
}
