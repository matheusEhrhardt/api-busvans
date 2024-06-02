package com.m4technology.busvans.domain.query;

public class LocalizacaoVeiculoQuery {

    public static final String buscarVeiculosMaisProximos = "select" +
            "    v.ID ID_VEICULO," +
            "    v.PLACA PLACA_VEICULO," +
            "    lv.LATITUDE," +
            "    lv.LONGITUDE" +
            "    from busvans.veiculo v" +
            "    join busvans.veiculo_rota vr on v.ID = vr.ID_VEICULO" +
            "    join busvans.rota r on r.id = vr.ID_ROTA" +
            "    join busvans.cidade c on c.id = r.local_partida" +
            "    join busvans.cidade c2 on c2.id = r.local_chegada" +
            "    join busvans.preco_passagem pp on r.local_chegada = pp.local_chegada and r.local_partida = pp.local_partida" +
            "    join localizacao_veiculo lv on lv.ID = v.ID_LOCALIZACAO" +
            "    where c.id = :local_partida" +
            "    and c2.id = :local_chegada" +
//            "    and vr.DIAS_SEMANA like :dia_semana" +
            "    and (v.tipo = :tipo or :tipo is null)" +
            "    and DATE_SUB(vr.HORA_SAIDA, INTERVAL 20 MINUTE) < DATE_FORMAT(NOW(), '%H:%i')" +
            "    and DATE_ADD(vr.hora_saida, INTERVAL pp.duracao_viagem hour) > DATE_FORMAT(NOW(), '%H:%i')";
}
