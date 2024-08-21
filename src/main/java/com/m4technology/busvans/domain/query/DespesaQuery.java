package com.m4technology.busvans.domain.query;

public class DespesaQuery {

    public static final String filtroPorSemana = "YEARWEEK(d.data, 1) = YEARWEEK(CURDATE(), 1) AND ";
    public static final String filtroPorMes = "YEAR(d.data) = YEAR(CURDATE()) AND MONTH(d.data) = MONTH(CURDATE()) AND ";
    public static final String filtroPorAno = "YEAR(d.data) = YEAR(CURDATE()) AND ";

    public static final String buscarDespesaPorPeriodo = "select sum(d.valor) valor,\n" +
            "    CASE d.tipo\n" +
            "        WHEN 'F' THEN 'Funcionário'\n" +
            "        WHEN 'C' THEN 'Combustível'\n" +
            "        WHEN 'O' THEN 'Outros'\n" +
            "    END AS tipo\n" +
            "from despesa d\n" +
            "where (d.data between :dataInicial and :dataFinal or :dataInicial is null and :dataFinal is null)\n" +
            "and d.ID_EMPRESA = :empresa\n" +
            "group by 2";
}
