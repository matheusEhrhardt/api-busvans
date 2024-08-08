package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TipoVeiculoEnum tipoVeiculo;
    @ManyToOne
    @JoinColumn(name="ID_EMPRESA")
    private Empresa empresa;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VeiculoRota> veiculoRotas;

    // motorista
    // cobrador
}
