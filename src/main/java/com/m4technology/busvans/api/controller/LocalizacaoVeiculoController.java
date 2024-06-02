package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.enums.StatusEnum;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.LocalizacaoVeiculo;
import com.m4technology.busvans.domain.service.LocalizacaoVeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Localizações Veiculos")
@RestController
@RequestMapping("/localizacao-veiculo")
public class LocalizacaoVeiculoController extends GenericController<LocalizacaoVeiculoService, LocalizacaoVeiculo> {

    @GetMapping("/buscar-rotas/{idPartida}/{idChegada}/{dataViagem}/{latitude}/{longitude}")
    public List<LocalizacaoVeiculoDTO> consultaResumoRotas(@PathVariable Long idPartida, @PathVariable Long idChegada,
                                                           @PathVariable LocalDate dataViagem, @PathVariable Double latitude,
                                                           @PathVariable Double longitude, @RequestParam(required = false) String tipoVeiculo){

        return service.buscarVeiculosProximos(idPartida,idChegada,dataViagem,latitude,longitude,tipoVeiculo);
    }

    @PostMapping("/atualizar-coordenada")
    public String registarLocalizacaoVeiculo(LocalizacaoVeiculoDTO localizacaoVeiculoDTO){
        StatusEnum statusRetorno = service.registrarLocalizacaoVeiculo(localizacaoVeiculoDTO);
        return statusRetorno.getDescricao();
    }


}
