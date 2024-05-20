package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PRECO_PASSAGEM")
public class PrecoPassagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="LOCAL_PARTIDA")
    private Cidade localPartida;
    @ManyToOne
    @JoinColumn(name="LOCAL_CHEGADA")
    private Cidade localChegada;
    private BigDecimal preco;

    @Column(name = "DURACAO_VIAGEM")
    private Double duracaoViagem;
}
