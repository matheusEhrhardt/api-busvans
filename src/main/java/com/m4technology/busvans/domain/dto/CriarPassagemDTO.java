package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class CriarPassagemDTO {

    @NotNull
    @JsonProperty("cliente")
    private Long clienteId;

    @NotNull
    @JsonProperty("id_veiculo_rota")
    private Long veiculoRotaId;

    @NotNull
    @Min(1)
    private Integer quantidade;

    private OffsetDateTime dataHoraCompra;

    @NotNull
    private LocalDate dataViagem;
}
