package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import lombok.Data;

import java.util.List;

@Data
public class RetornoPagamentoDTO {
    private String nomeCliente;
    private String cpfCliente;
    private StatusPagamentoEnum statusPagamento;
    private String localPartida;
    private String localChegada;
    private List<String> tickets;
}
