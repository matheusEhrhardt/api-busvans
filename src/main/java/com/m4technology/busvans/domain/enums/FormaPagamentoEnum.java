package com.m4technology.busvans.domain.enums;

import lombok.Getter;

@Getter
public enum FormaPagamentoEnum {
    PX("Pix"),
    DN("Dinheiro"),
    CC("Cartão de Crédito"),
    CD("Cartão de Débito");

    private String descricao;

    FormaPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public static FormaPagamentoEnum getByNome(String situacao) {
        switch (situacao) {
            case "PX":
                return FormaPagamentoEnum.PX;
            case "CC":
                return FormaPagamentoEnum.CC;
            case "CD":
                return FormaPagamentoEnum.CD;
            case "DN":
                return FormaPagamentoEnum.DN;
            default:
                return null;
        }
    }
}


