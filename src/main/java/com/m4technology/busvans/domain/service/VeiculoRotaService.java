package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.VeiculoRotaDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.query.RotaQuery;
import com.m4technology.busvans.domain.repository.VeiculoRepository;
import com.m4technology.busvans.domain.repository.VeiculoRotaDetailRepository;
import com.m4technology.busvans.domain.repository.VeiculoRotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoRotaService extends GenericService<VeiculoRotaRepository, VeiculoRota> {

    @Autowired
    private VeiculoRotaDetailRepository veiculoRotaDetailRepository;

    public VeiculoRotaService() {
        super(new VeiculoRota());
    }

    public List<VeiculoRotaDTO> consultaVeiculoRotas(){

        return veiculoRotaDetailRepository.consultaVeiculoRotas();
    }
}
