package com.gusta.mercadolivre.seguranca;

import com.gusta.mercadolivre.usuario.Usuario;
import com.gusta.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));
        return new MyUserDetails(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
