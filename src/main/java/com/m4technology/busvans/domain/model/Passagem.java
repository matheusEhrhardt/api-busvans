package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_veiculo_rota")
    private VeiculoRota veiculoRota;
    private Integer quantidade;
//    private BigDecimal valor;
    @CreationTimestamp
    @Column(name = "data_hora_compra")
    private LocalDateTime dataHoraCompra;
    @Column(name = "data_viagem")
    private LocalDate dataViagem;
    @OneToOne
    @JoinColumn(name="id_pagamento")
    private Pagamento pagamento;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
