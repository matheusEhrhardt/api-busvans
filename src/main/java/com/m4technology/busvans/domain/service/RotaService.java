package com.m4technology.busvans.domain.service;


import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.model.PrecoPassagem;
import com.m4technology.busvans.domain.model.Rota;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.repository.PrecoPassagemRepository;
import com.m4technology.busvans.domain.repository.RotaDetailRepository;
import com.m4technology.busvans.domain.repository.RotaRepository;
import com.m4technology.busvans.domain.repository.VeiculoRotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotaService extends GenericService<RotaRepository, Rota> {

    @Autowired
    private RotaDetailRepository rotaDetailRepository;

    @Autowired
    private VeiculoRotaRepository veiculoRotaRepository;

    @Autowired
    private PrecoPassagemRepository precoPassagemRepository;

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

    public List<ResumoRotaDTO> consultaRotasPorVeiculo(Long idVeiculo){

        try {
            List<VeiculoRota> veiculosRotas = veiculoRotaRepository.findByVeiculoId(idVeiculo);
            if (veiculosRotas == null || veiculosRotas.isEmpty()) {
                throw new com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException();
            }
            return veiculosRotas.stream()
                    .map(this::mapearVeiculoRotaParaResumo)
                    .collect(Collectors.toList());
        } catch (com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException("Ocorreu um erro ao consultar rotas disponíveis para o veículo informado.");
        }
    }

    private ResumoRotaDTO mapearVeiculoRotaParaResumo(VeiculoRota veiculoRota){
        ResumoRotaDTO dto = new ResumoRotaDTO();
        dto.setIdVeiculoRota(veiculoRota.getId());
        dto.setHoraSaida(veiculoRota.getHoraSaida());

        if (veiculoRota.getVeiculo() != null){
            dto.setPlacaVeiculo(veiculoRota.getVeiculo().getPlaca());
            dto.setTipoVeiculo(veiculoRota.getVeiculo().getTipoVeiculo());
        }

        if (veiculoRota.getRota() != null){
            Cidade partida = veiculoRota.getRota().getLocalPartida();
            Cidade chegada = veiculoRota.getRota().getLocalChegada();

            if (partida != null){
                dto.setIdLocalPartida(partida.getId());
                dto.setLocalPartida(partida.getNome());
            }
            if (chegada != null){
                dto.setIdLocalChegada(chegada.getId());
                dto.setLocalChegada(chegada.getNome());
            }

            PrecoPassagem precoPassagem = buscarPrecoPassagem(partida, chegada);
            if (precoPassagem != null){
                dto.setValorPassagem(precoPassagem.getPreco());
                dto.setHoraChegada(calcularHoraChegada(veiculoRota.getHoraSaida(), precoPassagem.getDuracaoViagem()));
            }
        }

        return dto;
    }

    private PrecoPassagem buscarPrecoPassagem(Cidade partida, Cidade chegada){
        if (partida == null || chegada == null){
            return null;
        }
        return precoPassagemRepository
                .findFirstByLocalPartidaIdAndLocalChegadaId(partida.getId(), chegada.getId())
                .orElse(null);
    }

    private LocalTime calcularHoraChegada(LocalTime horaSaida, Double duracaoViagem){
        if (horaSaida == null || duracaoViagem == null){
            return null;
        }
        long minutos = BigDecimal.valueOf(duracaoViagem)
                .multiply(BigDecimal.valueOf(60))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
        return horaSaida.plusMinutes(minutos);
    }
}
