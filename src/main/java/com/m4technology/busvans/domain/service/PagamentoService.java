package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.dto.RetornoPagamentoDTO;
import com.m4technology.busvans.domain.enums.StatusPagamentoEnum;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.*;
import com.m4technology.busvans.domain.repository.PagamentoRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.customer.CustomerCardClient;
import com.mercadopago.client.customer.CustomerCardCreateRequest;
import com.mercadopago.client.customer.CustomerClient;
import com.mercadopago.client.customer.CustomerRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPSearchRequest;
import com.mercadopago.resources.customer.Customer;
import com.mercadopago.resources.customer.CustomerCard;
import com.mercadopago.resources.customer.CustomerCardIssuer;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PagamentoService extends GenericService<PagamentoRepository, Pagamento> {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoRotaService veiculoRotaService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassagemService passagemService;

    public PagamentoService() {
        super(new Pagamento());
    }

    public RetornoPagamentoDTO pagar(PagamentoPassagemDTO pagamentoPassagem){

        StatusPagamentoEnum statusPagamento = processarPagamento(pagamentoPassagem);
        Cliente cliente = clienteService.salvar(pagamentoPassagem);
        VeiculoRota veiculoRota = veiculoRotaService.buscarPorId(pagamentoPassagem.getIdveiculoRota());
        Pagamento pagamento = salvar(pagamentoPassagem);
        Passagem passagem = passagemService.salvar(pagamento,cliente,veiculoRota,pagamentoPassagem);
        List<Ticket> tickets = new ArrayList<>();

        if (statusPagamento.equals(StatusPagamentoEnum.SUC)){
            tickets =  ticketService.salvar(passagem);
        }

        List<String> ticketsStr = tickets.stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());



        return new RetornoPagamentoDTO();
    }

    public Payment pagarPix() throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("TEST-994304876914658-060108-2aee50c533d2f24a644c16884310730c-1836763749");

        String operacaoID = UUID.randomUUID().toString();

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", operacaoID);

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal("100"))
                        .description("Título do produto")
                        .paymentMethodId("pix")
                        .dateOfExpiration(OffsetDateTime.of(2023, 1, 10, 10, 10, 10, 0, ZoneOffset.UTC))
                        .payer(
                                PaymentPayerRequest.builder()
                                        .email("matheus@gmail.com")
                                        .firstName("APRO")
                                        .identification(
                                                IdentificationRequest.builder().type("CPF").number("12345678909").build())
                                        .build())
                        .build();

        return client.create(paymentCreateRequest, requestOptions);

    }

    public Payment pagarCartao(){

        CustomerCard customerCard = new CustomerCard();
        customerCard.getCardholder();


        CustomerClient client = new CustomerClient();

        Map<String, Object> filters = new HashMap<>();
        filters.put("email", "test_payer_12345@testuser.com");

        MPSearchRequest searchRequest =
                MPSearchRequest.builder().offset(0).limit(0).build();

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());
        customHeaders.put("Content-Type", "application/json");
        customHeaders.put("Authorization", "Bearer TEST-994304876914658-060108-2aee50c533d2f24a644c16884310730c-1836763749");

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        try {
            client.search(searchRequest,requestOptions);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }

        MercadoPagoConfig.setAccessToken("TEST-994304876914658-060108-2aee50c533d2f24a644c16884310730c-1836763749");

        PaymentClient client2 = new PaymentClient();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal("100.00"))
                        .token("123")
                        .description("Passagem")
                        .installments(1)
                        .paymentMethodId("master")
                        .payer(
                                PaymentPayerRequest.builder()
                                        .email("matheusehrhardt2022@gmail.com")
                                        .firstName("APRO")
                                        .identification(
                                                IdentificationRequest.builder()
                                                        .type("CPF")
                                                        .number("5031433215406351")
                                                        .build())
                                        .build())
                        .build();

        try {
            return client2.create(paymentCreateRequest, requestOptions);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarClienteCartao(){

        MercadoPagoConfig.setAccessToken("TEST-994304876914658-060108-2aee50c533d2f24a644c16884310730c-1836763749");

        CustomerClient customerClient = new CustomerClient();
        CustomerCardClient customerCardClient = new CustomerCardClient();

        CustomerRequest customerRequest = CustomerRequest.builder()
                .email("teste@test.com")
                .build();
        Customer customer = null;
        try {
            customer = customerClient.create(customerRequest);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }

        CustomerCardIssuer issuer = CustomerCardIssuer.builder()
                .id(customer.getId())
                .build();

        CustomerCardCreateRequest cardCreateRequest = CustomerCardCreateRequest.builder()
                .token("9b2d63e00d66a8c721607214cedaecda")
                .issuer(issuer)
                .paymentMethodId("debit_card")
                .build();

        try {
            CustomerCard customerCard = customerCardClient.create(customer.getId(), cardCreateRequest);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
    }

    private StatusPagamentoEnum processarPagamento(PagamentoPassagemDTO pagamentoPassagem){
        return StatusPagamentoEnum.SUC;
    }

    private Pagamento salvar(PagamentoPassagemDTO pagamentoDTO){
        Pagamento pagamento = convertPagamentoDtoToPagamento(pagamentoDTO);
        return salvar(pagamento);
    }

    private Pagamento convertPagamentoDtoToPagamento(PagamentoPassagemDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setNomeCartao(pagamento.getNomeCartao());
        pagamento.setNumeroCartao(pagamentoDTO.getNumeroCartao());
        pagamento.setValidadeCartao(pagamentoDTO.getValidadeCartao());
        pagamento.setCvv(pagamentoDTO.getCvv());
        pagamento.setChavePix(pagamentoDTO.getChavePix());
        pagamento.setValorTotal(pagamentoDTO.getValorTotal());
        pagamento.setStatusPagamento(pagamentoDTO.getStatusPagamento());

        return pagamento;
    }
}
