package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class LocalizacaoVeiculoDTO {

    private Long idVeiculo;
    private String placa;
    private Long longitude;
    private Long latitude;
    @JsonIgnore
    private Double distancia;

    public LocalizacaoVeiculoDTO(ResultSet rs) throws SQLException {
        this.idVeiculo = rs.getLong("ID_VEICULO");
        this.placa = rs.getString("PLACA_VEICULO");
        this.longitude = rs.getLong("LONGITUDE");
        this.latitude = rs.getLong("LATITUDE");
    }

}
