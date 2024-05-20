package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.RetornoPagamentoDTO;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.model.Pagamento;
import com.m4technology.busvans.domain.service.ClienteService;
import com.m4technology.busvans.domain.service.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController extends GenericController<PagamentoService, Pagamento> {

    @PostMapping("/efetuar-pagamento")
    @ResponseStatus(HttpStatus.CREATED)
    public RetornoPagamentoDTO pagar(@RequestBody @Valid PagamentoPassagemDTO pagamentoPassagem){
        return  new RetornoPagamentoDTO();
    }
}
