package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.RetornoPagamentoDTO;
import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.*;
import com.m4technology.busvans.domain.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService extends GenericService<PagamentoRepository, Pagamento> {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoRotaService veiculoRotaService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassagemService passagemService;

    public PagamentoService() {
        super(new Pagamento());
    }

    public RetornoPagamentoDTO pagar(PagamentoPassagemDTO pagamentoPassagem){

        StatusPagamentoEnum statusPagamento = processarPagamento(pagamentoPassagem);
        Cliente cliente = clienteService.salvar(pagamentoPassagem);
        VeiculoRota veiculoRota = veiculoRotaService.buscarPorId(pagamentoPassagem.getIdveiculoRota());
        Pagamento pagamento = salvar(pagamentoPassagem);
        Passagem passagem = passagemService.salvar(pagamento,cliente,veiculoRota,pagamentoPassagem);
        List<Ticket> tickets = new ArrayList<>();

        if (statusPagamento.equals(StatusPagamentoEnum.SUC)){
            tickets =  ticketService.salvar(passagem);
        }

        List<String> ticketsStr = tickets.stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());



        return new RetornoPagamentoDTO();
    }

    private StatusPagamentoEnum processarPagamento(PagamentoPassagemDTO pagamentoPassagem){
        return StatusPagamentoEnum.SUC;
    }

    private Pagamento salvar(PagamentoPassagemDTO pagamentoDTO){
        Pagamento pagamento = convertPagamentoDtoToPagamento(pagamentoDTO);
        return salvar(pagamento);
    }

    private Pagamento convertPagamentoDtoToPagamento(PagamentoPassagemDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setNomeCartao(pagamento.getNomeCartao());
        pagamento.setNumeroCartao(pagamentoDTO.getNumeroCartao());
        pagamento.setValidadeCartao(pagamentoDTO.getValidadeCartao());
        pagamento.setCvv(pagamentoDTO.getCvv());
        pagamento.setChavePix(pagamentoDTO.getChavePix());
        pagamento.setValorTotal(pagamentoDTO.getValorTotal());
        pagamento.setStatusPagamento(pagamentoDTO.getStatusPagamento());

        return pagamento;
    }
}
