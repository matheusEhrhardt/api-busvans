package com.m4technology.busvans.domain.query;

public class RotaQuery {

    public static final String buscarRotasPorPartidaChegada = "select" +
            "    vr.id veiculo_rota_id," +
            "    v.tipo tipo_veiculo," +
            "    v.placa placa_veiculo," +
            "    c.id id_local_partida," +
            "    c.nome local_partida," +
            "    c2.id id_local_chegada," +
            "    c2.nome local_chegada," +
            "    vr.hora_saida," +
            "    DATE_ADD(vr.hora_saida, INTERVAL pp.duracao_viagem hour) hora_chegada," +
            "    pp.preco valor_passagem" +
            "    from busvans.veiculo v" +
            "    join busvans.veiculo_rota vr on v.id = vr.ID_VEICULO" +
            "    join busvans.rota r on r.id = vr.ID_ROTA" +
            "    join busvans.cidade c on c.id = r.local_partida" +
            "    join busvans.cidade c2 on c2.id = r.local_chegada" +
            "    join busvans.preco_passagem pp on r.local_chegada = pp.local_chegada and r.local_partida = pp.local_partida" +
            "    where c.id = :local_partida" +
            "    and c2.id = :local_chegada" +
            "    and vr.DIAS_SEMANA like :dia_semana" +
            "    and (v.tipo = :tipo or :tipo is null)" +
            "    order by vr.hora_saida" +
            "    LIMIT :limite OFFSET :pagina";

    public static final String buscarRotasPorPartidaChegadaAndVeiculo = "select" +
            "    vr.id veiculo_rota_id," +
            "    v.tipo tipo_veiculo," +
            "    v.placa placa_veiculo," +
            "    c.id id_local_partida," +
            "    c.nome local_partida," +
            "    c2.id id_local_chegada," +
            "    c2.nome local_chegada," +
            "    vr.hora_saida," +
            "    DATE_ADD(vr.hora_saida, INTERVAL pp.duracao_viagem hour) hora_chegada," +
            "    pp.preco valor_passagem" +
            "    from busvans.veiculo v" +
            "    join busvans.veiculo_rota vr on v.id = vr.ID_VEICULO" +
            "    join busvans.rota r on r.id = vr.ID_ROTA" +
            "    join busvans.cidade c on c.id = r.local_partida" +
            "    join busvans.cidade c2 on c2.id = r.local_chegada" +
            "    join busvans.preco_passagem pp on r.local_chegada = pp.local_chegada and r.local_partida = pp.local_partida" +
            "    where c.id = :local_partida" +
            "    and c2.id = :local_chegada" +
            "    and vr.DIAS_SEMANA like :dia_semana" +
            "    and v.id = :idVeiculo" +
            "    order by vr.hora_saida" +
            "    LIMIT 1";

}
