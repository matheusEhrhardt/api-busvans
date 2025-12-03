package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.model.Passagem;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassagemResponseDTO {
    private Long id;
    private Long clienteId;
    private Long veiculoRotaId;
    private VeiculoResumoDTO veiculo;
    private Integer quantidade;
    private LocalDateTime dataHoraCompra;
    private LocalDate dataViagem;

    public static PassagemResponseDTO from(Passagem passagem){
        PassagemResponseDTO dto = new PassagemResponseDTO();
        dto.setId(passagem.getId());
        dto.setClienteId(passagem.getCliente() != null ? passagem.getCliente().getId() : null);
        dto.setVeiculoRotaId(passagem.getVeiculoRota() != null ? passagem.getVeiculoRota().getId() : null);
        dto.setVeiculo(passagem.getVeiculoRota() != null ? VeiculoResumoDTO.from(passagem.getVeiculoRota().getVeiculo()) : null);
        dto.setQuantidade(passagem.getQuantidade());
        dto.setDataHoraCompra(passagem.getDataHoraCompra());
        dto.setDataViagem(passagem.getDataViagem());
        return dto;
    }
}
