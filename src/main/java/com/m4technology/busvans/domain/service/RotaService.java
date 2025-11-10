package com.m4technology.busvans.domain.service;


import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Rota;
import com.m4technology.busvans.domain.repository.RotaDetailRepository;
import com.m4technology.busvans.domain.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class RotaService extends GenericService<RotaRepository, Rota> {

    @Autowired
    private RotaDetailRepository rotaDetailRepository;

    public RotaService() {
        super(new Rota());
    }

    public Rota buscarPorPartidaChegada(Long idPartida, Long idChegada){
        Rota rota = repository.findFirstByLocalPartidaIdAndLocalChegadaId(idPartida, idChegada);
        if (rota == null) throw new com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException();
        return rota;
    }

    public List<ResumoRotaDTO> consultaResumoRotas(Long idPartida, Long idChegada, LocalDate dataViagem, String tipoVeiculo,int pagina, int limite){

        List<ResumoRotaDTO> retorno;
        DayOfWeek diaDaSemana;

        try {
            diaDaSemana = dataViagem.getDayOfWeek();
            retorno = rotaDetailRepository.consultaResumoRotas(idPartida, idChegada, diaDaSemana.getValue(),tipoVeiculo,pagina,limite);
        } catch (Exception e){
            throw new RuntimeException("Ocorreu um erro ao consultar rotas disponíveis para o endereço e data informada.");
        }
        return retorno;
    }

    public ResumoRotaDTO consultaRotaPorVeiculo(Long idPartida, Long idChegada, LocalDate dataViagem, Long idVeiculo){

        ResumoRotaDTO retorno;
        DayOfWeek diaDaSemana;

        try {
            diaDaSemana = dataViagem.getDayOfWeek();
            retorno = rotaDetailRepository.consultaRotaPorVeiculo(idPartida, idChegada, diaDaSemana.getValue(),idVeiculo);
        } catch (Exception e){
            throw new RuntimeException("Ocorreu um erro ao consultar rotas disponíveis para o endereço e data informada.");
        }
        return retorno;
    }
}
