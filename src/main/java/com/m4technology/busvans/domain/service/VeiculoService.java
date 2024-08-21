package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Empresa;
import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService extends GenericService<VeiculoRepository, Veiculo> {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private VeiculoRotaService veiculoRotaService;

    public VeiculoService() {
        super(new Veiculo());
    }

    @Override
    public Veiculo salvar(Veiculo model) {

        Empresa empresa = empresaService.buscarPorId(model.getIdEmpresa());

        if (empresa == null){
            throw new EntidadeNaoEncontradaException("Não existe empresa com o ID informado");
        }

        VeiculoRota veiculoRota = veiculoRotaService.buscarPorId(model.getIdVeiculoRota());

        if (veiculoRota == null){
            throw new EntidadeNaoEncontradaException("Não existe veiculo rota com o ID informado");
        }

        model.setEmpresa(empresa);
        model.setVeiculoRota(veiculoRota);

        return super.salvar(model);
    }

    public Optional<Veiculo> buscarPorPlaca(String placa){
        return repository.findByPlaca(placa)
                .stream()
                .findFirst();
    }
}
