package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.GraficoDespesaDTO;
import com.m4technology.busvans.domain.dto.GraficoFaturamentoDTO;
import com.m4technology.busvans.domain.dto.GraficoPassagemDTO;
import com.m4technology.busvans.domain.repository.DespesaDetailRepository;
import com.m4technology.busvans.domain.repository.PagamentoDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class GraficoService {

    @Autowired
    private PagamentoDetailRepository pagamentoRepository;
    @Autowired
    private DespesaDetailRepository despesaRepository;

    public List<GraficoFaturamentoDTO> consultarFaturamento(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){

        return pagamentoRepository.consultarFaturamento(empresa, periodo, dataInicial, dataFinal);
    }

    public List<GraficoPassagemDTO> consultarPassagensVendidas(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){

        return pagamentoRepository.consultarPassagensVendidas(empresa, periodo, dataInicial, dataFinal);
    }

    public List<GraficoDespesaDTO> consultarDespesa(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){

        return despesaRepository.consultarDespesa(empresa, periodo, dataInicial, dataFinal);
    }

}
