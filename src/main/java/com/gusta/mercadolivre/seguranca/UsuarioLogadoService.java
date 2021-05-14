package com.gusta.mercadolivre.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {
    public MyUserDetails getUsuarioLogado() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
