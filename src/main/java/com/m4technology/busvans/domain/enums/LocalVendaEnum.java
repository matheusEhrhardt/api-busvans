package com.m4technology.busvans.domain.enums;

public enum LocalVendaEnum {

    ONLINE("Online"),
    TERMINAL("Terminal"),
    VEICULO("Veiculo");

    private String descricao;

    LocalVendaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
