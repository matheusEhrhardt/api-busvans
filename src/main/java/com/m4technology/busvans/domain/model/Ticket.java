package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.StatusTicketEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private StatusTicketEnum status;
    @ManyToOne
    @JoinColumn(name="id_passagem")
    private Passagem passagem;

    public Ticket() {
    }

    public Ticket(String id, StatusTicketEnum status, Passagem passagem) {
        this.id = id;
        this.status = status;
        this.passagem = passagem;
    }
}
