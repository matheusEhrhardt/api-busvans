package com.m4technology.busvans.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DespesaDTO {

    private BigDecimal total;
    private BigDecimal combustivel;
    private BigDecimal funcionario;
    private BigDecimal outros;
}
