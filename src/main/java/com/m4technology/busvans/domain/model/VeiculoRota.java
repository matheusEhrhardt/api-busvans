package com.m4technology.busvans.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "veiculo_rota")
public class VeiculoRota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_veiculo")
    @JsonIgnore
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name="id_rota")
    private Rota rota;
    @Column(name = "dias_semana")
    private String diasSemana;
    @Column(name = "hora_saida")
    private LocalTime horaSaida;
    @Transient
    private List<String> diasSemanaList;

    public List<String> getDiasSemanaList() {
        return diasSemana != null ? Arrays.asList(diasSemana.split(",")) : null;
    }
}
