package com.m4technology.busvans.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassagemVendidaDTO {

    private Integer total;
    private Integer online;
    private Integer terminal;
    private Integer viagem;
    private Integer convenio;
}
