package com.m4technology.busvans.domain.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    REGISTRO_NAO_ENCONTRADO("/entidade-nao-encontrada","Não existe registro com o identificador informado"),
    ENTIDADE_ESTA_EM_USO("/entidade-em-uso","O registro não pode ser removido, pois está em uso"),
    ERRO_AO_PERSISTIR_OS_DADOS("/erro-ao-persistir","Ocorreu um erro ao tentar persistir os dados"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensível"),
    DADOS_INVALIDOS("/dados-invalidos","Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente."),
    ERRO("/error","Ocorreu um erro ao realizar a operação");

    private String descricao;
    private String uri;

    ExceptionEnum(String uri,String descricao) {
        this.uri = "Https://m4technology.com" + uri;
        this.descricao = descricao;
    }

}


