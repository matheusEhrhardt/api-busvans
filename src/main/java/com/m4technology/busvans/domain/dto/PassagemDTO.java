package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.FormaPagamentoEnum;
import com.m4technology.busvans.domain.enums.LocalVendaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PassagemDTO {
    private Long idVeiculoRota;
    private Integer quantidade;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamento;
    @Enumerated(EnumType.STRING)
    private LocalVendaEnum localVenda;
}
