package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.LocalizacaoVeiculoDTO;
import com.m4technology.busvans.domain.dto.ResumoRotaDTO;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Rota;
import com.m4technology.busvans.domain.service.RotaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Rotas")
@RestController
@RequestMapping("/rotas")
public class RotaController extends GenericController<RotaService, Rota> {

    @GetMapping("/buscar-rotas/{idPartida}/{idChegada}/{dataViagem}")
    public List<ResumoRotaDTO> consultaResumoRotas(@PathVariable Long idPartida, @PathVariable Long idChegada,
                                                   @PathVariable LocalDate dataViagem, @RequestParam(required = false) String tipoVeiculo,
                                                   @RequestParam int pagina,@RequestParam int limite){

        return service.consultaResumoRotas(idPartida,idChegada,dataViagem,tipoVeiculo,pagina,limite);
    }

    @GetMapping("/buscar-rota-veiculo/{idPartida}/{idChegada}/{idVeiculo}/{dataViagem}")
    public ResumoRotaDTO consultaRotaPorVeiculo(@PathVariable Long idPartida, @PathVariable Long idChegada,
                                                        @PathVariable LocalDate dataViagem, @PathVariable Long idVeiculo){

        return service.consultaRotaPorVeiculo(idPartida,idChegada,dataViagem,idVeiculo);
    }
}
