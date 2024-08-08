package com.m4technology.busvans.domain.dto;

import com.m4technology.busvans.domain.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;
  private PerfilEnum perfil;
  private Long veiculo;
  private Long empresa;
}
