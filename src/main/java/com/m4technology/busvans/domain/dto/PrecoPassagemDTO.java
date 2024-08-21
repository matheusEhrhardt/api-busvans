package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class PrecoPassagemDTO {

    private Long idveiculoRota;
    private String cidadePartida;
    private String cidadeChegada;
    private BigDecimal preco;

    public PrecoPassagemDTO(ResultSet rs) throws SQLException {
        this.cidadeChegada = rs.getString("cidade_chegada");
        this.cidadePartida = rs.getString("cidade_partida");
        this.preco = rs.getBigDecimal("preco");
        this.idveiculoRota = rs.getLong("veiculo_rota_id");
    }

}

