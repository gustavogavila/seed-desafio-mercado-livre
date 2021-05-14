package com.gusta.mercadolivre.imagemproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "imagens-produto")
public class ImagemProdutoController {

    @Autowired
    private UsuarioDonoProdutoValidator usuarioDonoProdutoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(usuarioDonoProdutoValidator);
    }

    @PostMapping
    public ResponseEntity<String> adicionar(@Valid @RequestBody ImagemProdutoRequest request) {
        return ResponseEntity.ok(request.toString());
    }
}
