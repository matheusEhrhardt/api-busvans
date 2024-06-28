package com.m4technology.busvans.domain.query;

public class PassagemQuery {

    public static final String buscarPassagemDisponivelPorEmail = "select c.email email," +
            "                   c.nome nome_cliente," +
            "                   c3.nome origem," +
            "                   c2.nome destino," +
            "                   t.ID ticket," +
            "                   vr.HORA_SAIDA," +
            "                   vr.ID_VEICULO veiculo," +
            "                   v.PLACA placa," +
            "                   v.MODELO modelo" +
            "               from cliente c" +
            "               join passagem p on c.id = p.ID_CLIENTE" +
            "               join ticket t on p.ID = t.ID_PASSAGEM" +
            "               join veiculo_rota vr on p.ID_VEICULO_ROTA = vr.ID" +
            "               join veiculo v on vr.ID_VEICULO = v.ID" +
            "               join rota r on r.id = vr.ID_ROTA" +
            "               join cidade c2 on c2.id = r.local_chegada" +
            "               join cidade c3 on c3.id = r.local_partida" +
            "               where c.email = :email" +
            "               and t.STATUS = 'D'";
}
