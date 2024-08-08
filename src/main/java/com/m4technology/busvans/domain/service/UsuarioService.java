package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.exception.EntidadeEmUsoExeption;
import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.exception.NegocioException;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Empresa;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UsuarioService extends GenericService<UsuarioRepository, Usuario> {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private EmpresaService empresaService;

    public UsuarioService() {
        super(new Usuario());
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Optional<Usuario>  usuarioExistente  = findByCodigoUsuario(usuario.getUsuario());

        if(usuarioExistente.isPresent()){
            throw new EntidadeEmUsoExeption("Já existe usuário cadastrado com codigo " + usuario.getUsuario());
        }

        if (usuario.getIdVeiculo() != null){
            Veiculo veiculo = veiculoService.buscarPorId(usuario.getIdVeiculo());
            usuario.setVeiculo(veiculo);
        }

        Empresa empresa = empresaService.buscarPorId(usuario.getIdEmpresa());

        if (empresa == null){
            throw new NegocioException("A empresa informada não existe");
        }

        usuario.setEmpresa(empresa);

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
        try {
            return repository.findByUsuario(codigoUsuario);
        } catch (RuntimeException e){
            throw new RuntimeException("Ocorreu um erro ao buscar usuario pelo codigo");
        }
    }
}
