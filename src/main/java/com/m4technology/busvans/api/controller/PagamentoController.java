package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.RetornoPagamentoDTO;
import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.model.Pagamento;
import com.m4technology.busvans.domain.service.ClienteService;
import com.m4technology.busvans.domain.service.PagamentoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos")
@RestController
@RequestMapping("api-bilheteria/pagamentos")
public class PagamentoController extends GenericController<PagamentoService, Pagamento> {

    @PostMapping("/efetuar-pagamento")
    @ResponseStatus(HttpStatus.CREATED)
    public RetornoPagamentoDTO pagar(@RequestBody @Valid PagamentoPassagemDTO pagamentoPassagem){
        return  new RetornoPagamentoDTO();
    }

    @GetMapping("/pix")
    public Payment pagarComPix() throws MPException, MPApiException {
        return service.pagarPix();
    }

    @GetMapping("/cartao")
    public Payment pagarComCartao() throws MPException, MPApiException {
        return service.pagarCartao();
    }

    @GetMapping("/cliente-cartao")
    public void cadas() {
        service.cadastrarClienteCartao();
    }
}
