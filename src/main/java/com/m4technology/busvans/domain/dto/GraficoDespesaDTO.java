package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class GraficoDespesaDTO {

    private String tipo;
    private BigDecimal valor;

    public GraficoDespesaDTO() {
    }

    public GraficoDespesaDTO(ResultSet rs) throws SQLException {
        this.tipo = rs.getString("tipo");
        this.valor = rs.getBigDecimal("valor");
    }
}
