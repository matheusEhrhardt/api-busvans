package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService extends GenericService<VeiculoRepository, Veiculo> {

    public VeiculoService() {
        super(new Veiculo());
    }

    public Optional<Veiculo> buscarPorPlaca(String placa){
        return repository.findByPlaca(placa)
                .stream()
                .findFirst();
    }
}
