package com.m4technology.busvans.domain.repository;

import com.m4technology.busvans.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsTicketById(String id);
}
