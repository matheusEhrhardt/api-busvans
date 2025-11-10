package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.service.VeiculoRotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Veiculos Rotas")
@RestController
@RequestMapping("/veiculos-rotas")
public class VeiculoRotaController extends GenericController<VeiculoRotaService, VeiculoRota> {

    @GetMapping("/cidades/{idVeiculo}")
    public List<Cidade> buscarCidadesPorVeiculo(@PathVariable Long idVeiculo){
        return service.buscarCidadesPorVeiculo(idVeiculo);
    }
}
