package com.m4technology.busvans.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank
    @Email
    @JsonAlias({"username","user"})
    private String email;

    @NotBlank
    @JsonAlias({"password","pass"})
    private String senha;
}
