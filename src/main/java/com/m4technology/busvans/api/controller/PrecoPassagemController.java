package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.dto.PrecoPassagemDTO;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.PrecoPassagem;
import com.m4technology.busvans.domain.service.PrecoPassagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Preço passagens")
@RestController
@RequestMapping("api-bilheteria/preco-passagens")
public class PrecoPassagemController extends GenericController<PrecoPassagemService, PrecoPassagem> {

    @GetMapping("/tabela-precos/{veiculo}")
    public List<PrecoPassagemDTO> buscarTabelaPrecos(@PathVariable Long veiculo){
        return service.consultaPrecoPassagem(veiculo);
    }

}
