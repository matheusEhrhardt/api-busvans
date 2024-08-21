package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.*;
import com.m4technology.busvans.domain.service.GraficoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Tag(name = "Graficos")
@RestController
@RequestMapping("graficos")
public class GraficoController {

    @Autowired
    private GraficoService service;

    @GetMapping("/faturamento/{empresa}")
    private List<GraficoFaturamentoDTO> consultarFaturamento(@PathVariable Long empresa,
                                                             @RequestParam(required = false) String periodo,
                                                             @RequestParam(required = false) LocalDate dataInicial,
                                                             @RequestParam(required = false) LocalDate dataFinal){

        return service.consultarFaturamento(empresa,periodo, dataInicial, dataFinal);
    }

    @GetMapping("/despesa/{empresa}")
    private List<GraficoDespesaDTO> consultarDespesa(@PathVariable Long empresa,
                                                             @RequestParam(required = false) String periodo,
                                                             @RequestParam(required = false) LocalDate dataInicial,
                                                             @RequestParam(required = false) LocalDate dataFinal){

        return service.consultarDespesa(empresa,periodo, dataInicial, dataFinal);
    }

    @GetMapping("/passagens-vendidas/{empresa}")
    private List<GraficoDespesaDTO> consultarPassagensVendidas(@PathVariable Long empresa,
                                                     @RequestParam(required = false) String periodo,
                                                     @RequestParam(required = false) LocalDate dataInicial,
                                                     @RequestParam(required = false) LocalDate dataFinal){

        return service.consultarDespesa(empresa,periodo, dataInicial, dataFinal);
    }
}
