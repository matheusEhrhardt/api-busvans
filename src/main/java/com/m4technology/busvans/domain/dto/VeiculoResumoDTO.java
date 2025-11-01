package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResumoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private TipoVeiculoEnum tipo;
}
