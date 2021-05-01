package com.gusta.mercadolivre.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private Instant criadoEm;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, String senha, Instant criadoEm) {
        this.login = login;
        this.senha = senha;
        this.criadoEm = criadoEm;
    }
}
