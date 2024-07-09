package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Usuario;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.service.UsuarioService;
import com.m4technology.busvans.domain.service.VeiculoRotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Veiculos Rotas")
@RestController
@RequestMapping("api-bilheteria/veiculos-rotas")
public class VeiculoRotaController extends GenericController<VeiculoRotaService, VeiculoRota> {
}
