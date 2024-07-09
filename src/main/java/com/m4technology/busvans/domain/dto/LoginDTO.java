package com.m4technology.busvans.domain.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String usuario;

    private String senha;

    public LoginDTO() {
    }

    public LoginDTO(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

}
