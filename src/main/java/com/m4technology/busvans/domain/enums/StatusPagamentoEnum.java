package com.m4technology.busvans.domain.enums;

public enum StatusPagamentoEnum {

    SUC("Sucesso"),
    PEN("Pendente"),
    CAN("Cancelado"),
    EXP("Expirado"),
    REC("Recusado");

    private String descricao;

    StatusPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }
}
