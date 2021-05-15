package com.gusta.mercadolivre.imagemproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "imagens-produto")
public class ImagemProdutoController {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private UsuarioDonoProdutoValidator usuarioDonoProdutoValidator;
//
//    @InitBinder
//    public void init(WebDataBinder binder) {
//        binder.addValidators(usuarioDonoProdutoValidator);
//    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> subirImagemParaSistemaDeArquivos(@Valid @RequestParam MultipartFile file,
                                                                   @RequestParam Long produtoId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("produtos-imagens/" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/produto/" + produtoId + "/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(fileDownloadUri);
    }
}
