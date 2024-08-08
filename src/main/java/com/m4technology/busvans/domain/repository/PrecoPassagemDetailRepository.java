package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.PrecoPassagemDTO;
import com.m4technology.busvans.domain.query.PrecoPassagemQuery;
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
public class PrecoPassagemDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<PrecoPassagemDTO> consultaPrecoPassagem(Long veiculo) {

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("veiculo", veiculo);

        return namedTemplateAmzcred
                .query(PrecoPassagemQuery.buscarPrecosPassagens, parameters, (rs, rowNum) -> new PrecoPassagemDTO(rs));


    }
}
