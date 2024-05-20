package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService extends GenericService<VeiculoRepository, Veiculo> {

    public VeiculoService() {
        super(new Veiculo());
    }
}
