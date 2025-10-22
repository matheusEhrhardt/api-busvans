package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "rota")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="local_partida")
    private Cidade localPartida;
    @ManyToOne
    @JoinColumn(name="local_chegada")
    private Cidade localChegada;

    @ManyToMany
    @JoinTable(
            name = "rota_cidade",
            joinColumns = @JoinColumn(name = "id_rota"),
            inverseJoinColumns = @JoinColumn(name = "id_cidade")
    )
    private List<Cidade> possiveislocaisChegada;
    @ManyToMany
    @JoinTable(
            name = "rota_cidade",
            joinColumns = @JoinColumn(name = "id_rota"),
            inverseJoinColumns = @JoinColumn(name = "id_cidade")
    )
    private List<Cidade> possiveislocaisPartida;
}
