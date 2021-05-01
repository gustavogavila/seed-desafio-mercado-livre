package com.gusta.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmailUnicoValidator emailUnicoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailUnicoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        em.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
