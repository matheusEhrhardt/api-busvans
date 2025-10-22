package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.FormaPagamentoEnum;
import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_cartao")
    private String nomeCartao;
    @Column(name = "numero_cartao")
    private Long numeroCartao;
    @Column(name = "validade_cartao")
    private String validadeCartao;
    private Integer cvv;
    @Column(name = "chave_pix")
    private String chavePix;
    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamento;
    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;
    @Column(name = "valor")
    private BigDecimal valorTotal;

}
