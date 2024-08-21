package com.m4technology.busvans.domain.enums;

public enum StatusTicketEnum {

    D("Disponivel"),
    I("Indisponivel");

    private String descricao;

    StatusTicketEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
