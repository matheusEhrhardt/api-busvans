package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.AuthenticationRequest;
import com.m4technology.busvans.domain.dto.AuthenticationResponse;
import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    public AuthenticationResponse refreshToken(AuthenticationRequest request){
        Optional<Usuario> usuario = usuarioService.findByCodigoUsuario(request.getUsername());

        if(usuario.isEmpty()){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }

        boolean isAcessoLiberado = usuario.get().getSenha().equals(request.getPassword());

        if(!isAcessoLiberado){
            throw new EntidadeNaoEncontradaException("Usuário ou senha inválidos");
        }

        var user = usuarioService.findByCodigoUsuario(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Optional<Usuario> user = usuarioService.findByCodigoUsuario(request.getUsername());

        if (user.isEmpty()){
            throw new EntidadeNaoEncontradaException("Não foi encontrado nenhum usuário com as credencias informadas");
        }

        Usuario usuario = user.get();

        var jwtToken = jwtService.generateToken(usuario);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .perfil(usuario.getPerfil())
                .veiculo(usuario.getVeiculo() == null ? null : usuario.getVeiculo().getId())
                .empresa(usuario.getEmpresa() == null ? null : usuario.getEmpresa().getId())
                .build();
    }
}
