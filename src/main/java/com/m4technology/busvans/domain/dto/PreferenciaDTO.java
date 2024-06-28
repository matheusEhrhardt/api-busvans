package com.m4technology.busvans.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PreferenciaDTO {
    private String titulo;
    private String descricao;
    private Integer quantidade;
    private BigDecimal valorUnitario;
}
