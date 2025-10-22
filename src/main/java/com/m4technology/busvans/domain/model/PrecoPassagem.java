package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "preco_passagem")
public class PrecoPassagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="local_partida")
    private Cidade localPartida;
    @ManyToOne
    @JoinColumn(name="local_chegada")
    private Cidade localChegada;
    private BigDecimal preco;

    @Column(name = "duracao_viagem")
    private Double duracaoViagem;
}
