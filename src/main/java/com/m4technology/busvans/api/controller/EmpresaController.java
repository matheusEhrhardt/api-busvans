package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Empresa;
import com.m4technology.busvans.domain.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Empresas")
@RestController
@RequestMapping("api-bilheteria/empresas")
public class EmpresaController extends GenericController<EmpresaService, Empresa> {
}
