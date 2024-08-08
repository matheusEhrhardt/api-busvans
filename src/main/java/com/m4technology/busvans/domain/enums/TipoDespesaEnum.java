package com.m4technology.busvans.domain.enums;

public enum TipoDespesaEnum {

    F("Funcionário"),
    C("Combustível"),
    O("Outros");

    private String descricao;

    TipoDespesaEnum(String descricao) {
        this.descricao = descricao;
    }
}
