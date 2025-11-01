package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.m4technology.busvans.domain.exception.CredenciaisInvalidasException;

@Service
public class UsuarioService extends GenericService<UsuarioRepository, Usuario> {

    public UsuarioService() {
        super(new Usuario());
    }

    public Usuario autenticar(String email, String senha){
        Usuario usuario = repository.findByEmailIgnoreCase(email)
                .orElseThrow(CredenciaisInvalidasException::new);
        if (!usuario.getSenha().equals(senha)){
            throw new CredenciaisInvalidasException();
        }
        return usuario;
    }
}
