package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Despesa;
import com.m4technology.busvans.domain.service.DespesaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Despesa")
@RestController
@RequestMapping("despesas")
public class DespesaController extends GenericController<DespesaService, Despesa> {
}


