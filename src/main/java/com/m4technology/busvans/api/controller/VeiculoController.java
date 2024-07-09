package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.service.VeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Veiculos")
@RestController
@RequestMapping("api-bilheteria/veiculos")
public class VeiculoController extends GenericController<VeiculoService, Veiculo> {
}
