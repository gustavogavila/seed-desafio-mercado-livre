package com.gusta.mercadolivre.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailUnicoValidator implements Validator {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmailUnicoValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        UsuarioRequest request = (UsuarioRequest) o;
        Optional<Usuario> possivelUsuario = usuarioRepository.findByLogin(request.getLogin());

        possivelUsuario.ifPresent(usuario -> {
            errors.rejectValue("login", "EmailJaExiste", "E-mail já existe");
        });
    }
}
