package com.m4technology.busvans.api.controller;


import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Ticket;
import com.m4technology.busvans.domain.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tickets")
@RestController
@RequestMapping("api-bilheteria/tickets")
public class TicketController extends GenericController<TicketService, Ticket> {
}


