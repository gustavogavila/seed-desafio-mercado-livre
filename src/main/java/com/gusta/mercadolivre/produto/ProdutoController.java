package com.gusta.mercadolivre.produto;

import com.gusta.mercadolivre.seguranca.MyUserDetails;
import com.gusta.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private CaracteristicasDiferentesValidator caracteristicasDiferentesValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(caracteristicasDiferentesValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criar(@Valid @RequestBody ProdutoRequest request,
                                         @AuthenticationPrincipal MyUserDetails usuarioLogado) {
        Produto produto = request.toModel(entityManager, usuarioLogado.getId());
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }
}
