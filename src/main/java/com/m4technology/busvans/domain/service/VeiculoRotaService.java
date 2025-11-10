package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.repository.VeiculoRotaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class VeiculoRotaService extends GenericService<VeiculoRotaRepository, VeiculoRota> {

    public VeiculoRotaService() {
        super(new VeiculoRota());
    }

    public VeiculoRota buscarPorRotaId(Long rotaId){
        VeiculoRota veiculoRota = repository.findFirstByRotaId(rotaId);
        if (veiculoRota == null) throw new com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException();
        return veiculoRota;
    }

    public List<Cidade> buscarCidadesPorVeiculo(Long veiculoId){
        List<VeiculoRota> veiculosRotas = repository.findByVeiculoId(veiculoId);
        if (veiculosRotas == null || veiculosRotas.isEmpty()) {
            throw new com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException();
        }

        Set<Cidade> cidades = new LinkedHashSet<>();

        for (VeiculoRota veiculoRota : veiculosRotas) {
            if (veiculoRota.getRota() != null) {
                if (veiculoRota.getRota().getLocalPartida() != null) {
                    cidades.add(veiculoRota.getRota().getLocalPartida());
                }
                if (veiculoRota.getRota().getLocalChegada() != null) {
                    cidades.add(veiculoRota.getRota().getLocalChegada());
                }
            }
        }

        if (cidades.isEmpty()) {
            throw new com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException();
        }

        return new ArrayList<>(cidades);
    }
}
