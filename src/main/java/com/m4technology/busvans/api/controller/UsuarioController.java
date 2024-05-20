package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends GenericController<UsuarioService, Usuario> {
}
