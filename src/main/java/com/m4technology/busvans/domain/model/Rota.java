package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="LOCAL_PARTIDA")
    private Cidade localPartida;
    @ManyToOne
    @JoinColumn(name="LOCAL_CHEGADA")
    private Cidade localChegada;

    @ManyToMany
    @JoinTable(
            name = "ROTA_CIDADE",
            joinColumns = @JoinColumn(name = "ID_ROTA"),
            inverseJoinColumns = @JoinColumn(name = "ID_CIDADE")
    )
    private List<Cidade> possiveislocaisChegada;
    @ManyToMany
    @JoinTable(
            name = "ROTA_CIDADE",
            joinColumns = @JoinColumn(name = "ID_ROTA"),
            inverseJoinColumns = @JoinColumn(name = "ID_CIDADE")
    )
    private List<Cidade> possiveislocaisPartida;
}
