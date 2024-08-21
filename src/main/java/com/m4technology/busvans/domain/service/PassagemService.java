package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.PassagemDTO;
import com.m4technology.busvans.domain.dto.PassagemDisponivelDTO;
import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import com.m4technology.busvans.domain.enums.StatusTicketEnum;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.*;
import com.m4technology.busvans.domain.repository.CidadeRepository;
import com.m4technology.busvans.domain.repository.PassagemDetailRepository;
import com.m4technology.busvans.domain.repository.PassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassagemService extends GenericService<PassagemRepository, Passagem> {

    @Autowired
    private PassagemDetailRepository detailRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private VeiculoRotaService veiculoRotaService;
    @Autowired
    private PagamentoService pagamentoService;

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

    public void comprarPassagem(PassagemDTO passagemDTO){

        VeiculoRota veiculoRota = veiculoRotaService.buscarPorId(passagemDTO.getIdVeiculoRota());

        Pagamento pagamento = Pagamento.builder()
                .valorTotal(passagemDTO.getValorTotal())
                .statusPagamento(StatusPagamentoEnum.SUC)
                .formaPagamento(passagemDTO.getFormaPagamento())
                .build();

        pagamento = pagamentoService.salvar(pagamento);

        Passagem passagem = Passagem.builder()
                .dataViagem(LocalDate.now())
                .dataHoraCompra(LocalDateTime.now())
                .localVenda(passagemDTO.getLocalVenda())
                .pagamento(pagamento)
                .quantidade(passagemDTO.getQuantidade())
                .veiculoRota(veiculoRota)
                .build();

        passagem = salvar(passagem);

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < passagemDTO.getQuantidade(); i++){
            Ticket ticket = ticketService.salvar(new Ticket(StatusTicketEnum.I,passagem));
            tickets.add(ticket);
        }
    }
}
