package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.model.Passagem;
import com.m4technology.busvans.domain.service.ClienteService;
import com.m4technology.busvans.domain.service.PassagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Passagens")
@RestController
@RequestMapping("/passagens")
public class PassagemController extends GenericController<PassagemService, Passagem> {
}
