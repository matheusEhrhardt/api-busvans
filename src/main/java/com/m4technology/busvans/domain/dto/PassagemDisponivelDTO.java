package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Data
public class PassagemDisponivelDTO {
    private String email;
    private String nomeCliente;
    private String origem;
    private String destino;
    private LocalTime horaSaida;
    private String ticket;
    private Long veiculo;
    private String placa;
    private String modelo;
    @JsonIgnore
    private List<String> tickets;

    public PassagemDisponivelDTO(ResultSet rs) throws SQLException {
        this.email = rs.getString("email");
        this.nomeCliente = rs.getString("nome_cliente");
        this.origem = rs.getString("origem");
        this.destino = rs.getString("destino");
        this.ticket = rs.getString("ticket");
        this.horaSaida = rs.getObject("HORA_SAIDA",LocalTime.class);
        this.veiculo = rs.getLong("veiculo");
        this.placa = rs.getString("placa");
        this.modelo = rs.getString("modelo");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassagemDisponivelDTO that = (PassagemDisponivelDTO) o;
        return origem.equals(that.origem) && destino.equals(that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origem, destino);
    }
}
