package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.LocalVendaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "LOCAL_VENDA")
    @Enumerated(EnumType.STRING)
    private LocalVendaEnum localVenda;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
