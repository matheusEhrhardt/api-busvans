package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

@Data
public class ResumoRotaDTO {
    private Long idVeiculoRota;
    private TipoVeiculoEnum tipoVeiculo;
    private String placaVeiculo;
    private String localPartida;
    private String localChegada;
    private LocalTime horaSaida;
    private LocalTime horaChegada;
    private BigDecimal valorPassagem;
    public ResumoRotaDTO(){

    }
    public ResumoRotaDTO(ResultSet rs) throws SQLException {
        this.idVeiculoRota = rs.getLong("veiculo_rota_id");
        this.localPartida = rs.getString("local_partida");
        this.localChegada = rs.getString("local_chegada");
        this.horaSaida = rs.getObject("hora_saida", LocalTime.class);
        this.horaChegada = rs.getObject("hora_chegada", LocalTime.class);
        this.valorPassagem = rs.getBigDecimal("valor_passagem");
        this.tipoVeiculo = TipoVeiculoEnum.getValue(rs.getString("tipo_veiculo"));
        this.placaVeiculo = rs.getString("placa_veiculo");
    }
}
