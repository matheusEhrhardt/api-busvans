package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.PrecoPassagem;
import com.m4technology.busvans.domain.service.PrecoPassagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Preço passagens")
@RestController
@RequestMapping("/preco-passagens")
public class PrecoPassagemController extends GenericController<PrecoPassagemService, PrecoPassagem> {
}
