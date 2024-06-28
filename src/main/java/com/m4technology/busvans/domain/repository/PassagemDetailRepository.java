package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.dto.PassagemDisponivelDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.query.PassagemQuery;
import com.m4technology.busvans.domain.query.RotaQuery;
import com.m4technology.busvans.domain.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings({"deprecation", "SpringJavaInjectionPointsAutowiringInspection"})
public class PassagemDetailRepository {

    @Autowired
    @Qualifier("busvansDataSource")
    private DataSource datasource;

    public List<PassagemDisponivelDTO> consultaPassagemDisponiveis(String email){

        NamedParameterJdbcTemplate namedTemplateAmzcred = new NamedParameterJdbcTemplate(datasource);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);

        //List<PassagemDisponivelDTO> passagens =
        return namedTemplateAmzcred
                .query(PassagemQuery.buscarPassagemDisponivelPorEmail,parameters, (rs, rowNum) -> new PassagemDisponivelDTO(rs));

        //return buidPassagemDisponivel(passagens);

    }

    public List<PassagemDisponivelDTO> buidPassagemDisponivel(List<PassagemDisponivelDTO> passagens){
        if (passagens.isEmpty()) return null;

        List<PassagemDisponivelDTO> passagensAgrupadas = new ArrayList<>();

//        passagens.forEach(p -> {

        for (PassagemDisponivelDTO p : passagens) {

            if (passagensAgrupadas.isEmpty()){
                p.setTickets(Arrays.asList(p.getTicket()));
                passagensAgrupadas.add(p);
            } else {
                for (PassagemDisponivelDTO pa : passagensAgrupadas) {
                    if (pa.equals(p)){
                        List<String> tickets = new ArrayList<>(pa.getTickets());
                        tickets.add(p.getTicket());
                        pa.setTickets(tickets);

                    } else {
                        pa.setTickets(Arrays.asList(pa.getTicket()));
                        passagensAgrupadas.add(pa);
                    }
                }
            }
        }


//        for (PassagemDisponivelDTO passagem : passagens) {
//
//            if (passagensAgrupadas.isEmpty()){
//                passagensAgrupadas.add(passagem);
//            } else {
//                passagensAgrupadas.stream()
//                        .filter(p -> p.getOrigem().equals(passagem.getOrigem()))
//                        .filter(p -> p.getDestino().equals(passagem.getDestino()))
//            }
//
//        }
//
//
//        List<String> tickets = passagens.stream().map(PassagemDisponivelDTO::getTicket).collect(Collectors.toList());
//
//        PassagemDisponivelDTO passagem = passagens.stream().findFirst().orElse(null);
//        passagem.setTickets(tickets);
        return passagensAgrupadas;

    }
}
