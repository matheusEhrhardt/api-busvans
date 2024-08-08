package com.m4technology.busvans.domain.query;

public class PrecoPassagemQuery {

//    public static final String buscarPrecosPassagens = "select" +
//            "    c.nome cidade_chegada," +
//            "    c2.nome cidade_partida," +
//            "    pp.preco" +
//            "    from preco_passagem pp" +
//            "    join cidade c on c.id = pp.local_chegada" +
//            "    join cidade c2 on c2.id = pp.local_partida";


    public static final String buscarPrecosPassagens = "select c.nome cidade_chegada, c2.nome cidade_partida, pp.preco " +
            "from veiculo_rota vr " +
            "join rota r on vr.ID_ROTA = r.id " +
            "join cidade c on c.id = r.local_chegada " +
            "join cidade c2 on c2.id = r.local_partida " +
            "join preco_passagem pp on c.id = pp.local_chegada and pp.local_partida = c2.id " +
            "where vr.ID_VEICULO = :veiculo " +
            "order by 1,2";
}
