package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.dto.PreferenciaDTO;
import com.m4technology.busvans.domain.service.MercadoPagoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mercado Pago")
@RestController
@RequestMapping("/mercado-pago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService service;

    @PostMapping("/criar-preferencia")
    public Preference criarPreferencia(@RequestBody PreferenciaDTO preferencia) {
        return service.criarPreferencia(preferencia);
    }
}
