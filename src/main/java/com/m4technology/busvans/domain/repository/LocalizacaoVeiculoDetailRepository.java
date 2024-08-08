package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.query.LocalizacaoVeiculoQuery;
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
public class LocalizacaoVeiculoDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<LocalizacaoVeiculoDTO> consultaVeiculosMaisProximos(Long idPartida, Long idChegada, Integer diaSemana, String tipoVeiculo){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("local_partida", idPartida);
        parameters.put("local_chegada", idChegada);
        parameters.put("dia_semana", "%" + diaSemana + "%");
        parameters.put("tipo", tipoVeiculo);

        return namedTemplateAmzcred
                .query(LocalizacaoVeiculoQuery.buscarVeiculosMaisProximos,parameters, (rs, rowNum) -> new LocalizacaoVeiculoDTO(rs));

    }

    public LocalizacaoVeiculoDTO consultaLocalizacaoVeiculo(Long idVeiculo){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("veiculo", idVeiculo);

        List<LocalizacaoVeiculoDTO> localizacaoVeiculos = namedTemplateAmzcred
                .query(LocalizacaoVeiculoQuery.buscarLocalizacaoPorVeiculo,parameters, (rs, rowNum) -> new LocalizacaoVeiculoDTO(rs));

        return localizacaoVeiculos.stream().findFirst().orElse(null);
    }


}
