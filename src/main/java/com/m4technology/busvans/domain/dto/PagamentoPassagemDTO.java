package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoPassagemDTO {
    private String nomeCliente;
    private String cpfCliente;
    private LocalDate dataNascimento;
    private String email;
    private Long contato;
    private String nomeCartao;
    private Long numeroCartao;
    private String validadeCartao;
    private Integer cvv;
    private String chavePix;
    private String formaPagamento;
    private BigDecimal valorTotal;
    private LocalDate dataViagem;
    private Integer quantidadePassagem;
    private Long idveiculoRota;
    @JsonIgnore
    private StatusPagamentoEnum statusPagamento;
}
