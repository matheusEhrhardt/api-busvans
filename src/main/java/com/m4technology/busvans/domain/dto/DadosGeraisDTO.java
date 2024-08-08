package com.m4technology.busvans.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DadosGeraisDTO{

    private FaturamentoDTO faturamento;
    private PassagemVendidaDTO passagemVendida;
    private DespesaDTO despesa;
}
