package com.m4technology.busvans.domain.query;

public class PrecoPassagemQuery {

    public static final String buscarPrecosPassagens = "select" +
            "    c.nome cidade_chegada," +
            "    c2.nome cidade_partida," +
            "    pp.preco" +
            "    from preco_passagem pp" +
            "    join cidade c on c.id = pp.local_chegada" +
            "    join cidade c2 on c2.id = pp.local_partida";
}
