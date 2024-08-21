package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.GraficoDespesaDTO;
import com.m4technology.busvans.domain.dto.GraficoFaturamentoDTO;
import com.m4technology.busvans.domain.query.DespesaQuery;
import com.m4technology.busvans.domain.query.PagamentoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"deprecation", "SpringJavaInjectionPointsAutowiringInspection"})
public class DespesaDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<GraficoDespesaDTO> consultarDespesa(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){


        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        String sql = DespesaQuery.buscarDespesaPorPeriodo;

        if (periodo != null) {

            String condicao = adicionarCondicaoPorPeriodo(periodo);
            sql = sql.replace("where",condicao);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataInicial", dataInicial);
        parameters.put("dataFinal", dataFinal);
        parameters.put("empresa", empresa);

        return namedTemplateAmzcred
                .query(sql,parameters, (rs, rowNum) -> new GraficoDespesaDTO(rs));

    }

    public String adicionarCondicaoPorPeriodo(String periodo){
        String condicao = "where ";

        switch (periodo) {
            case "S": // Semanal
                condicao += DespesaQuery.filtroPorSemana;
                break;
            case "M": // Mensal
                condicao += DespesaQuery.filtroPorMes;
                break;
            case "A": // Anual
                condicao += DespesaQuery.filtroPorAno;
                break;
        }

        return condicao;
    }
}
