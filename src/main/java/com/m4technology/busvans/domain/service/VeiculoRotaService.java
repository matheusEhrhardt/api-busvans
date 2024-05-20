package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.repository.VeiculoRepository;
import com.m4technology.busvans.domain.repository.VeiculoRotaRepository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoRotaService extends GenericService<VeiculoRotaRepository, VeiculoRota> {

    public VeiculoRotaService() {
        super(new VeiculoRota());
    }
}
