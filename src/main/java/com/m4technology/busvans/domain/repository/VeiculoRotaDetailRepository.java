package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.dto.VeiculoRotaDTO;
import com.m4technology.busvans.domain.query.RotaQuery;
import com.m4technology.busvans.domain.query.VeiculoRotaQuery;
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
public class VeiculoRotaDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<VeiculoRotaDTO> consultaVeiculoRotas(){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        return namedTemplateAmzcred
                .query(VeiculoRotaQuery.buscarDetalhado, (rs, rowNum) -> new VeiculoRotaDTO(rs));

    }

}
