package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController extends GenericController<CidadeService, Cidade> {
}


