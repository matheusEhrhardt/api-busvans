package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.query.RotaQuery;
import com.m4technology.busvans.domain.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"deprecation", "SpringJavaInjectionPointsAutowiringInspection"})
public class RotaDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<ResumoRotaDTO> consultaResumoRotas(Long idPartida, Long idChegada, Integer diaSemana, String tipoVeiculo, int pagina, int limite){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);
        int paginaCalc = PaginationUtils.getPagina(pagina,limite);
        int limiteCalc = PaginationUtils.getLimite(pagina,limite);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("local_partida", idPartida);
        parameters.put("local_chegada", idChegada);
        parameters.put("dia_semana", "%" + diaSemana + "%");
        parameters.put("tipo", tipoVeiculo);
        parameters.put("pagina", paginaCalc);
        parameters.put("limite", limiteCalc);

        return namedTemplateAmzcred
                .query(RotaQuery.buscarRotasPorPartidaChegada,parameters, (rs, rowNum) -> new ResumoRotaDTO(rs));

    }

    public ResumoRotaDTO consultaRotaPorVeiculo(Long idPartida, Long idChegada, Integer diaSemana, Long idVeiculo){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("local_partida", idPartida);
        parameters.put("local_chegada", idChegada);
        parameters.put("dia_semana", "%" + diaSemana + "%");
        parameters.put("idVeiculo", idVeiculo);

        return namedTemplateAmzcred
                .query(RotaQuery.buscarRotasPorPartidaChegadaAndVeiculo,parameters, (rs, rowNum) -> new ResumoRotaDTO(rs))
                .stream().findFirst().orElse(null);

    }
}
