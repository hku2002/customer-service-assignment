package com.connie.customer.application;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.api.dto.ModifyTicketRequest;
import com.connie.customer.application.Factory.TicketTypeFactory;
import com.connie.customer.application.strategy.TicketTypeStrategy;
import com.connie.customer.domain.entity.Ticket;
import com.connie.customer.domain.entity.TicketHandler;
import com.connie.customer.domain.entity.TicketHandlerIndex;
import com.connie.customer.domain.entity.TicketMapping;
import com.connie.customer.domain.repository.TicketHandlerIndexRepository;
import com.connie.customer.domain.repository.TicketHandlerRepository;
import com.connie.customer.domain.repository.TicketMappingRepository;
import com.connie.customer.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.connie.customer.domain.enums.TicketType.PROBLEM_INQUIRY;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketHandlerRepository ticketHandlerRepository;
    private final TicketMappingRepository ticketMappingRepository;
    private final TicketHandlerIndexRepository ticketHandlerIndexRepository;
    private final TicketTypeFactory ticketTypeFactory;

    @Transactional
    public void createTicket(CreateTicketRequest request) {
        TicketTypeStrategy ticketTypeStrategy = ticketTypeFactory.findStrategy(request.type());
        ticketTypeStrategy.checkTypeField(request);
        Ticket ticket = ticketRepository.save(Ticket.from(request));
        assignmentTicket(ticket);
    }

    @Transactional
    public void modifyTicket(ModifyTicketRequest request) {
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElseThrow();
        ticket.modifyTitle(request.title());
        ticket.modifyContents(request.contents());
    }

    private void assignmentTicket(Ticket ticket) {
        if (PROBLEM_INQUIRY.equals(ticket.getType())) {
            TicketHandler minTicketHandler = ticketHandlerRepository.findFirstHandlerHasMinTicket();
            ticketMappingRepository.save(TicketMapping.of(minTicketHandler, ticket));
            return;
        }

        List<TicketHandler> ticketHandlers = ticketHandlerRepository.findAll();
        TicketHandlerIndex ticketHandlerIndex = ticketHandlerIndexRepository.findById(1L).orElseThrow();
        long latestHandlerId = ticketHandlerIndex.getHandler().getId();
        TicketHandler nextTicketHandler = findNextTicketHandler(ticketHandlers, latestHandlerId);
        ticketMappingRepository.save(TicketMapping.of(nextTicketHandler, ticket));
    }

    /**
     * 라운드 로빈 방식 다음 티켓 할당 로직
     * @param ticketHandlers 티켓 담당자 목록
     * @param latestHandlerId 마지막 티켓 담당자 아이디
     * @return TicketHandler 다음 티켓 담당자
     */
    private TicketHandler findNextTicketHandler(List<TicketHandler> ticketHandlers, long latestHandlerId) {
        return ticketHandlers.stream()
                .filter(handler -> latestHandlerId + 1L == handler.getId())
                .findFirst()
                .orElseGet(() -> ticketHandlers.stream()
                        .filter(handler -> 1L == handler.getId())
                        .findFirst()
                        .orElseThrow());
    }

}
