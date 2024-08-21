package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.PassagemDTO;
import com.m4technology.busvans.domain.dto.PassagemDisponivelDTO;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Passagem;
import com.m4technology.busvans.domain.service.PassagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Passagens")
@RestController
@RequestMapping("api-bilheteria/passagens")
public class PassagemController extends GenericController<PassagemService, Passagem> {

    @GetMapping("/pssagem-disponiveis/{email}")
    public List<PassagemDisponivelDTO> consultaPassagemDisponiveis(@PathVariable String email){
        return service.consultaPassagemDisponiveis(email);
    }

    @PostMapping("/comprar")
    @ResponseStatus(HttpStatus.CREATED)
    public void comprarPassagem(@RequestBody PassagemDTO passagem){
        service.comprarPassagem(passagem);
    }
}
