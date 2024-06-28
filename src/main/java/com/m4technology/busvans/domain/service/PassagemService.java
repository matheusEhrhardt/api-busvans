package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.PassagemDisponivelDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.*;
import com.m4technology.busvans.domain.repository.CidadeRepository;
import com.m4technology.busvans.domain.repository.PassagemDetailRepository;
import com.m4technology.busvans.domain.repository.PassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassagemService extends GenericService<PassagemRepository, Passagem> {

    @Autowired
    private PassagemDetailRepository detailRepository;

    public PassagemService() {
        super(new Passagem());
    }

    public Passagem salvar(Pagamento pagamento, Cliente cliente, VeiculoRota veiculoRota, PagamentoPassagemDTO pagamentoPassagem){

        Passagem passagem = new Passagem();
        passagem.setCliente(cliente);
        passagem.setPagamento(pagamento);
        passagem.setVeiculoRota(veiculoRota);
        passagem.setDataViagem(pagamentoPassagem.getDataViagem());
        passagem.setQuantidade(pagamentoPassagem.getQuantidadePassagem());

        return salvar(passagem);
    }

    public List<PassagemDisponivelDTO> consultaPassagemDisponiveis(String email){
        return detailRepository.consultaPassagemDisponiveis(email);
    }
}
