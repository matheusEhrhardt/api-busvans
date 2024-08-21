package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class VeiculoRotaDTO {

    private Long id;
    private String cidadePartida;
    private String cidadeChegada;

    public VeiculoRotaDTO(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.cidadeChegada = rs.getString("local_chegada");
        this.cidadePartida = rs.getString("local_partida");
    }
}
