package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.GraficoFaturamentoDTO;
import com.m4technology.busvans.domain.dto.GraficoPassagemDTO;
import com.m4technology.busvans.domain.dto.PassagemDisponivelDTO;
import com.m4technology.busvans.domain.query.DespesaQuery;
import com.m4technology.busvans.domain.query.PagamentoQuery;
import com.m4technology.busvans.domain.query.PassagemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

@Repository
@SuppressWarnings({"deprecation", "SpringJavaInjectionPointsAutowiringInspection"})
public class PagamentoDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<GraficoFaturamentoDTO> consultarFaturamento(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){


        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        String sql = PagamentoQuery.buscarFaturamentoPorPeriodo;

        if (periodo != null) {

            String condicao = adicionarCondicaoPorPeriodo(periodo);
            sql = sql.replace("where",condicao);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataInicial", dataInicial);
        parameters.put("dataFinal", dataFinal);
        parameters.put("empresa", empresa);

        return namedTemplateAmzcred
                .query(sql,parameters, (rs, rowNum) -> new GraficoFaturamentoDTO(rs));

    }

    public List<GraficoPassagemDTO> consultarPassagensVendidas(Long empresa, String periodo, LocalDate dataInicial, LocalDate dataFinal){


        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        String sql = PagamentoQuery.buscarPassagemPorPeriodo;

        if (periodo != null) {

            String condicao = adicionarCondicaoPorPeriodo(periodo);
            sql = sql.replace("where",condicao);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataInicial", dataInicial);
        parameters.put("dataFinal", dataFinal);
        parameters.put("empresa", empresa);

        return namedTemplateAmzcred
                .query(sql,parameters, (rs, rowNum) -> new GraficoPassagemDTO(rs));

    }

    public String adicionarCondicaoPorPeriodo(String periodo){
        String condicao = "where ";

        switch (periodo) {
            case "S": // Semanal
                condicao += PagamentoQuery.filtroPorSemana;
                break;
            case "M": // Mensal
                condicao += PagamentoQuery.filtroPorMes;
                break;
            case "A": // Anual
                condicao += PagamentoQuery.filtroPorAno;
                break;
        }

        return condicao;
    }
}
