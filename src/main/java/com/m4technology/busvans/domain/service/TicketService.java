package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.enums.StatusTicketEnum;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.model.Passagem;
import com.m4technology.busvans.domain.model.Ticket;
import com.m4technology.busvans.domain.repository.CidadeRepository;
import com.m4technology.busvans.domain.repository.TicketRepository;
import com.m4technology.busvans.domain.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService extends GenericService<TicketRepository, Ticket> {

    public TicketService() {
        super(new Ticket());
    }

    public List<Ticket> salvar(Passagem passagem){

        int index = 0;
        List<Ticket> tickets = new ArrayList<>();

        while (passagem.getQuantidade() > index){
            String identifiicador = gerarIdentificador();
            Ticket ticket = new Ticket(identifiicador, StatusTicketEnum.D, passagem);
            tickets.add(ticket);
            index++;
        }

        return salvar(tickets);
    }

    private String gerarIdentificador(){

        String identifiicador = StringUtils.generateRandomString();
        Boolean existeRegistro = repository.existsTicketById(identifiicador);

        if (existeRegistro) gerarIdentificador();

        return identifiicador;
    }
}
