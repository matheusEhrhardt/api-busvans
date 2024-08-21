package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class GraficoPassagemDTO {

    private String local;
    private BigDecimal valor;

    public GraficoPassagemDTO() {
    }

    public GraficoPassagemDTO(ResultSet rs) throws SQLException {
        this.local = rs.getString("local");
        this.valor = rs.getBigDecimal("valor");
    }
}
