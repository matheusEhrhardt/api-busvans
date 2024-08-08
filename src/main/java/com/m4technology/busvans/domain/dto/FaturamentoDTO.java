package com.m4technology.busvans.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FaturamentoDTO {

    private BigDecimal total;
    private BigDecimal valorLiquido;
    private DespesaDTO despesa;
}
