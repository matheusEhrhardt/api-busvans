package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class GraficoFaturamentoDTO {

    private String mes;
    private BigDecimal valor;

    public GraficoFaturamentoDTO() {
    }

    public GraficoFaturamentoDTO(ResultSet rs) throws SQLException {
        this.mes = rs.getString("mes");
        this.valor = rs.getBigDecimal("valor");
    }
}
