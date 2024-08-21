package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import jakarta.persistence.*;
import lombok.Data;

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
    @ManyToOne
    @JoinColumn(name="ID_VEICULO_ROTA")
    private VeiculoRota veiculoRota;
    @Transient
    private Long idEmpresa;
    @Transient
    private Long idVeiculoRota;

}
