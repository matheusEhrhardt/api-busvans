package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.AuthenticationRequest;
import com.m4technology.busvans.domain.dto.AuthenticationResponse;
import com.m4technology.busvans.domain.dto.LoginDTO;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.service.AuthenticationService;
import com.m4technology.busvans.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

    @PostMapping("/login")
    public  ResponseEntity<Usuario> login(@RequestBody LoginDTO login){
        Usuario usuario = usuarioService.login(login.getUsuario(),login.getSenha());
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

}