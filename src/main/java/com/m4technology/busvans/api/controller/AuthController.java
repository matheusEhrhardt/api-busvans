package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.LoginRequestDTO;
import com.m4technology.busvans.domain.dto.LoginResponseDTO;
import com.m4technology.busvans.domain.dto.VeiculoResumoDTO;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.m4technology.busvans.config.JwtTokenProvider;

import java.util.HashMap;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request){
        Usuario usuario = usuarioService.autenticar(request.getEmail(), request.getSenha());

        VeiculoResumoDTO veiculoResumo = null;
        Veiculo v = usuario.getVeiculo();
        if (v != null){
            veiculoResumo = new VeiculoResumoDTO(
                    v.getID(),
                    v.getPlaca(),
                    v.getModelo(),
                    v.getAno(),
                    v.getTipoVeiculo()
            );
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        if (v != null) {
            claims.put("idVeiculo", v.getID());
        }
        String token = jwtTokenProvider.generateToken(usuario.getEmail(), claims);

        LoginResponseDTO resp = new LoginResponseDTO();
        resp.setIdUsuario(usuario.getId());
        resp.setNome(usuario.getNome());
        resp.setEmail(usuario.getEmail());
        resp.setVeiculo(veiculoResumo);
        resp.setToken(token);
        return resp;
    }
}
