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
    @Column(name = "NOME_CARTAO")
    private String nomeCartao;
    @Column(name = "NUMERO_CARTAO")
    private Long numeroCartao;
    @Column(name = "VALIDADE_CARTAO")
    private String validadeCartao;
    private Integer cvv;
    @Column(name = "CHAVE_PIX")
    private String chavePix;
    @Column(name = "FORMA_PAGAMENTO")
    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamento;
    @Column(name = "STATUS_PAGAMENTO")
    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;
    @Column(name = "VALOR")
    private BigDecimal valorTotal;

}
