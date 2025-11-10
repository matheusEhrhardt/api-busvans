package com.m4technology.busvans.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GerarPassagemDTO {
    private Long idCidadePartida;
    private Long idCidadeChegada;
    private Long idCliente;
    private LocalDate dataViagem;
    private Integer quantidade;
}
