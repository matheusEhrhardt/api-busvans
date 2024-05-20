package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends GenericService<UsuarioRepository, Usuario> {

    public UsuarioService() {
        super(new Usuario());
    }
}
