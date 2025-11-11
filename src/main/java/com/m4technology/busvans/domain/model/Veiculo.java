package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoVeiculoEnum tipoVeiculo;
    @ManyToOne
    @JoinColumn(name="id_empresa")
    private Empresa empresa;
    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VeiculoRota> veiculoRotas;
    @OneToOne
    @JoinColumn(name="id_localizacao")
    private LocalizacaoVeiculo localizacao;

    // motorista
    // cobrador
}
