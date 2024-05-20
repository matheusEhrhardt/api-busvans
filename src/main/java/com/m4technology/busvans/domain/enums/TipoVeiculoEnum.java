package com.m4technology.busvans.domain.enums;

import lombok.Getter;

@Getter
public enum TipoVeiculoEnum {
    V("Van"),
    O("Ônibus");

    private String descricao;

    TipoVeiculoEnum(String descricao) {
        this.descricao = descricao;
    }
    public static TipoVeiculoEnum getValue(String tipo) {
        switch (tipo) {
            case "V":
                return TipoVeiculoEnum.V;
            case "O":
                return TipoVeiculoEnum.O;
            default:
                return null;
        }
    }
}


