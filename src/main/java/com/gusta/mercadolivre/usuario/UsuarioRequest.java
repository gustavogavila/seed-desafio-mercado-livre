package com.gusta.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.time.Instant;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Min(6)
    private String senha;

    @NotNull
    @PastOrPresent
    private Instant criadoEm;

    public UsuarioRequest(String login, String senha, Instant criadoEm) {
        this.login = login;
        this.senha = senha;
        this.criadoEm = Instant.now();
    }

    public String getLogin() {
        return login;
    }

    public Usuario toModel() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new Usuario(login, passwordEncoder.encode(senha), criadoEm);
    }
}
