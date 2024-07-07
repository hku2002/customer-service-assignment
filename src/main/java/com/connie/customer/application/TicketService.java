package com.connie.customer.application;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.application.Factory.TicketTypeFactory;
import com.connie.customer.application.strategy.TicketTypeStrategy;
import com.connie.customer.domain.entity.Ticket;
import com.connie.customer.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketTypeFactory ticketTypeFactory;

    @Transactional
    public void createTicket(CreateTicketRequest request) {
        TicketTypeStrategy ticketTypeStrategy = ticketTypeFactory.findStrategy(request.type());
        ticketTypeStrategy.checkTypeField(request);
        ticketRepository.save(Ticket.from(request));
    }

}
