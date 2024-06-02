package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class LocalizacaoVeiculoDTO {

    private Long idVeiculo;
    private String placa;
    private Double longitude;
    private Double latitude;
    @JsonIgnore
    private Double distancia;

    public LocalizacaoVeiculoDTO() {}

    public LocalizacaoVeiculoDTO(Long idVeiculo, String placa, Double longitude, Double latitude) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocalizacaoVeiculoDTO(ResultSet rs) throws SQLException {
        this.idVeiculo = rs.getLong("ID_VEICULO");
        this.placa = rs.getString("PLACA_VEICULO");
        this.longitude = rs.getDouble("LONGITUDE");
        this.latitude = rs.getDouble("LATITUDE");
    }

}
