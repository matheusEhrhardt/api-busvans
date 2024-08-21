package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.DadosGeraisDTO;
import com.m4technology.busvans.domain.dto.DespesaDTO;
import com.m4technology.busvans.domain.dto.FaturamentoDTO;
import com.m4technology.busvans.domain.dto.PassagemVendidaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "Clientes")
@RestController
@RequestMapping("dados-gerais")
public class DadosGeraisController {

    @GetMapping("/{empresa}")
    private DadosGeraisDTO buscarDadosGerais(@PathVariable Long empresa, @RequestParam(required = false) String periodo,
                                             @RequestParam(required = false) Date dataInicial,
                                             @RequestParam(required = false) Date dataFinal){

        return DadosGeraisDTO.builder()
                .faturamento(FaturamentoDTO.builder().build())
                .passagemVendida(PassagemVendidaDTO.builder().build())
                .despesa(DespesaDTO.builder().build())
                .build();
    }
}
