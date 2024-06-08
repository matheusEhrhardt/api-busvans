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
    @JoinColumn(name="ID_CLIENTE")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="ID_VEICULO_ROTA")
    private VeiculoRota veiculoRota;
    private Integer quantidade;
    @CreationTimestamp
    @Column(name = "DATA_HORA_COMPRA")
    private LocalDateTime dataHoraCompra;
    @Column(name = "DATA_VIAGEM")
    private LocalDate dataViagem;
    @OneToOne
    @JoinColumn(name="ID_PAGAMENTO")
    private Pagamento pagamento;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
