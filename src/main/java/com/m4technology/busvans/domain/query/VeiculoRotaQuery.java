package com.m4technology.busvans.domain.query;

public class VeiculoRotaQuery {

    public static final String buscarDetalhado = "select\n" +
            "    vr.ID,\n" +
            "    c2.nome local_partida,\n" +
            "    c.nome local_chegada\n" +
            "from veiculo_rota vr\n" +
            "join rota r on r.id = vr.ID_ROTA\n" +
            "join cidade c on c.id = r.local_chegada\n" +
            "join cidade c2 on c2.id = r.local_partida";
}
