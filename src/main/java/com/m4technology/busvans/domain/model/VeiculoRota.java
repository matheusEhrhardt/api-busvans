package com.m4technology.busvans.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "VEICULO_ROTA")
public class VeiculoRota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="ID_VEICULO")
    @JsonIgnore
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name="ID_ROTA")
    private Rota rota;
    @Column(name = "DIAS_SEMANA")
    private String diasSemana;
    @Column(name = "HORA_SAIDA")
    private LocalTime horaSaida;
    @Transient
    private List<String> diasSemanaList;

    public List<String> getDiasSemanaList() {
        return diasSemana != null ? Arrays.asList(diasSemana.split(",")) : null;
    }
}
