package com.gusta.mercadolivre.seguranca;

public class LoginRequest {
    private String email;
    private String senha;

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
