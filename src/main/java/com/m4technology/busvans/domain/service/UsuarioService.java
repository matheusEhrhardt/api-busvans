package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UsuarioService extends GenericService<UsuarioRepository, Usuario> {

    @Autowired
    private PasswordEncoder encoder;

    public UsuarioService() {
        super(new Usuario());
    }

    public Usuario cadastrar(Usuario usuario){
        Optional<Usuario> usuarioExistente = findByCodigoUsuario(usuario.getUsuario());

        if(usuarioExistente.isPresent()){
            throw new RuntimeException("Já existe usuário cadastrado com codigo " + usuario.getUsuario());
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Usuario login(String codigoUsuario, String senha){

        Optional<Usuario> usuario =  findByCodigoUsuario(codigoUsuario);

        if(usuario.isEmpty()){
            throw new EntidadeNaoEncontradaException("Login ou senha inválidos");
        }

        Boolean acessoLiberado = encoder.matches(senha, usuario.get().getSenha());

        if(!acessoLiberado){
            throw new EntidadeNaoEncontradaException("Login ou senha inválidos");
        }

        return usuario.orElse(null);
    }

    public Optional<Usuario> findByCodigoUsuario(String codigoUsuario){
        return repository.findByUsuario(codigoUsuario);
    }
}
