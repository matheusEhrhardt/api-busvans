package com.m4technology.busvans.domain.query;

public class PagamentoQuery {

    public static final String filtroPorSemana = "YEARWEEK(pa.DATA_HORA_COMPRA, 1) = YEARWEEK(CURDATE(), 1) AND ";
    public static final String filtroPorMes = "YEAR(pa.DATA_HORA_COMPRA) = YEAR(CURDATE()) AND MONTH(pa.DATA_HORA_COMPRA) = MONTH(CURDATE()) AND ";
    public static final String filtroPorAno = "YEAR(pa.DATA_HORA_COMPRA) = YEAR(CURDATE()) AND ";

    public static final String buscarFaturamentoPorPeriodo = "Select\n" +
            "    SUM(pg.VALOR) valor,\n" +
            "    CASE MONTH(pa.DATA_HORA_COMPRA)\n" +
            "        WHEN 1 THEN 'Jan'\n" +
            "        WHEN 2 THEN 'Fev'\n" +
            "        WHEN 3 THEN 'Mar'\n" +
            "        WHEN 4 THEN 'Abr'\n" +
            "        WHEN 5 THEN 'Mai'\n" +
            "        WHEN 6 THEN 'Jun'\n" +
            "        WHEN 7 THEN 'Jul'\n" +
            "        WHEN 8 THEN 'Ago'\n" +
            "        WHEN 9 THEN 'Set'\n" +
            "        WHEN 10 THEN 'Out'\n" +
            "        WHEN 11 THEN 'Nov'\n" +
            "        WHEN 12 THEN 'Dez'\n" +
            "    END AS Mes,\n" +
            "    MONTH(pa.DATA_HORA_COMPRA) " +
            "from passagem pa\n" +
            "join pagamento pg on pa.ID_PAGAMENTO = pg.ID\n" +
            "join veiculo_rota vr on pa.ID_VEICULO_ROTA = vr.ID\n" +
            "join veiculo v on v.ID_VEICULO_ROTA = vr.ID\n" +
            "join empresa e on e.id = v.ID_EMPRESA " +
            "where (pg.STATUS_PAGAMENTO = 'SUC')\n" +
            "and (pa.DATA_HORA_COMPRA between :dataInicial and :dataFinal or :dataInicial is null and :dataFinal is null)\n" +
            "and e.id = :empresa " +
            "group by 2,3 " +
            "ORDER BY 3";

    public static final String buscarPassagemPorPeriodo = "Select\n" +
            "    SUM(pg.VALOR) valor,\n" +
            "    CASE pa.LOCAL_VENDA\n" +
            "        WHEN 'VEICULO' THEN 'Veiculo'\n" +
            "        WHEN 'TERMINAL' THEN 'Terminal'\n" +
            "        WHEN 'ONLINE' THEN 'Online'\n" +
            "    END AS local\n" +
            "from passagem pa\n" +
            "join pagamento pg on pa.ID_PAGAMENTO = pg.ID\n" +
            "join veiculo_rota vr on pa.ID_VEICULO_ROTA = vr.ID\n" +
            "join veiculo v on v.ID = vr.ID_VEICULO\n" +
            "join empresa e on e.id = v.ID_EMPRESA\n" +
            "where pg.STATUS_PAGAMENTO = 'SUC'\n" +
            "and e.id = :empresa\n" +
            "and (pa.DATA_HORA_COMPRA between :dataInicial and :dataFinal or :dataInicial is null and :dataFinal is null)\n" +
            "group by 2";
}
