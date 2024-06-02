package com.m4technology.busvans.domain.enums;

public enum StatusEnum {

    SUC("Sucesso"),
    ERR("Error");

    private String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
