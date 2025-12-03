package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.TipoVeiculoEnum;
import com.m4technology.busvans.domain.model.Veiculo;
import lombok.Data;

@Data
public class VeiculoResumoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private TipoVeiculoEnum tipo;

    public static VeiculoResumoDTO from(Veiculo veiculo){
        if (veiculo == null) return null;

        VeiculoResumoDTO dto = new VeiculoResumoDTO();
        dto.setId(veiculo.getId());
        dto.setPlaca(veiculo.getPlaca());
        dto.setModelo(veiculo.getModelo());
        dto.setAno(veiculo.getAno());
        dto.setTipo(veiculo.getTipoVeiculo());
        return dto;
    }
}
