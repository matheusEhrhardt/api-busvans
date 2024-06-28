package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PreferenciaDTO;
import com.m4technology.busvans.domain.utils.StringUtils;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MercadoPagoService {

    public Preference criarPreferencia(PreferenciaDTO preferencia) {

        MercadoPagoConfig.setAccessToken("APP_USR-7428836633568483-060709-59e10256110702b74f0f355c87255d36-1836763749");

        try {
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(StringUtils.generateID())
                            .title(preferencia.getTitulo())
                            .description(preferencia.getDescricao())
                            .quantity(preferencia.getQuantidade())
                            .currencyId("BRL")
                            .unitPrice(preferencia.getValorUnitario())
                            .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);


            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(PreferenceBackUrlsRequest.builder()
                            .success("myapp://success")
                            .pending("myapp://pending")
                            .failure("myapp://failure")
                            .build())
                    .autoReturn("approved")
                    .paymentMethods(PreferencePaymentMethodsRequest.builder()
                            .excludedPaymentMethods(
                                    Arrays.asList(PreferencePaymentMethodRequest.builder().id("bolbradesco").build(),
                                            PreferencePaymentMethodRequest.builder().id("pec").build())
                            ).build())
                    .build();

            PreferenceClient client = new PreferenceClient();

            return client.create(preferenceRequest);
        } catch (MPException | MPApiException e){
            throw new RuntimeException("Ocorreu um erro ao criar preferência. Cause: " + e.getMessage());
        }
    }
}
