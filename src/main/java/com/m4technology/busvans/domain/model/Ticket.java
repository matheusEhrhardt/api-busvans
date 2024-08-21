package com.m4technology.busvans.domain.model;

import com.m4technology.busvans.domain.enums.StatusTicketEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusTicketEnum status;
    @ManyToOne
    @JoinColumn(name="ID_PASSAGEM")
    private Passagem passagem;

    public Ticket() {
    }

    public Ticket(StatusTicketEnum status, Passagem passagem) {
        this.status = status;
        this.passagem = passagem;
    }
}
