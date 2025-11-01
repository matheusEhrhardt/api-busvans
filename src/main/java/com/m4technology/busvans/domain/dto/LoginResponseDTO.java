package com.m4technology.busvans.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private VeiculoResumoDTO veiculo;
    private String token;
}
