package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.exception.NegocioException;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.LocalizacaoVeiculo;
import com.m4technology.busvans.domain.repository.LocalizacaoVeiculoDetailRepository;
import com.m4technology.busvans.domain.repository.LocalizacaoVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoVeiculoService extends GenericService<LocalizacaoVeiculoRepository, LocalizacaoVeiculo> {

    @Autowired
    private LocalizacaoVeiculoDetailRepository localizacaoVeiculoRepository;

    public LocalizacaoVeiculoService() {
        super(new LocalizacaoVeiculo());
    }

    public List<LocalizacaoVeiculoDTO> buscarVeiculosProximos(Long idPartida,Long idChegada,LocalDate dataViagem, Long latitude,
                                                              Long longitude, String tipoVeiculo) {

        if (!dataViagem.equals(LocalDate.now())){
            throw new NegocioException("A data informada precisa ser igual a hoje.");
        }

        DayOfWeek diaDaSemana = dataViagem.getDayOfWeek();

        List<LocalizacaoVeiculoDTO> localizacaoVeiculo = localizacaoVeiculoRepository
                .consultaVeiculosMaisProximos(idPartida,idChegada,diaDaSemana.getValue(),tipoVeiculo);

        for (LocalizacaoVeiculoDTO localizacao : localizacaoVeiculo) {
            double distancia = calcularDistancia(latitude,longitude,localizacao.getLatitude(),localizacao.getLongitude());
            localizacao.setDistancia(distancia);
        }

        localizacaoVeiculo.sort((v1, v2) -> v2.getDistancia().compareTo(v1.getDistancia()));

        localizacaoVeiculo = localizacaoVeiculo.stream()
                .limit(5)
                .collect(Collectors.toList());

        return localizacaoVeiculo;
    }

    public double calcularDistancia(double latitudeCliente, double longitudeCliente,
                                           double latitudeVeiculo, double longitudeVeiculo) {

        int EARTH_RADIUS_KM = 6371; // Raio da Terra em km
        double dLat = Math.toRadians(latitudeVeiculo - latitudeCliente);
        double dLon = Math.toRadians(longitudeVeiculo - longitudeCliente);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latitudeCliente)) * Math.cos(Math.toRadians(latitudeVeiculo)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
