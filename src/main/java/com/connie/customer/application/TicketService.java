package com.connie.customer.application;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.application.Factory.TicketTypeFactory;
import com.connie.customer.application.strategy.TicketTypeStrategy;
import com.connie.customer.domain.entity.Ticket;
import com.connie.customer.domain.entity.TicketHandler;
import com.connie.customer.domain.entity.TicketMapping;
import com.connie.customer.domain.repository.TicketHandlerRepository;
import com.connie.customer.domain.repository.TicketMappingRepository;
import com.connie.customer.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.connie.customer.domain.enums.TicketType.PROBLEM_INQUIRY;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketHandlerRepository ticketHandlerRepository;
    private final TicketMappingRepository ticketMappingRepository;
    private final TicketTypeFactory ticketTypeFactory;

    @Transactional
    public void createTicket(CreateTicketRequest request) {
        TicketTypeStrategy ticketTypeStrategy = ticketTypeFactory.findStrategy(request.type());
        ticketTypeStrategy.checkTypeField(request);
        Ticket ticket = ticketRepository.save(Ticket.from(request));
        assignmentTicket(ticket);
    }

    private void assignmentTicket(Ticket ticket) {
        if (PROBLEM_INQUIRY.equals(ticket.getType())) {
            TicketHandler minTicketHandler = ticketHandlerRepository.findFirstHandlerHasMinTicket();
            ticketMappingRepository.save(TicketMapping.of(minTicketHandler, ticket));
            return;
        }

        // TODO 라운드 로빈
    }

}
