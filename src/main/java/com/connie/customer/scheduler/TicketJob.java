package com.connie.customer.scheduler;

import com.connie.customer.api.dto.request.CreateTicketRequest;
import com.connie.customer.application.TicketService;
import com.connie.customer.application.messagequeue.LocalMessageQueue;
import com.connie.customer.domain.entity.Ticket;
import com.connie.customer.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class TicketJob {

    private final TicketRepository ticketRepository;
    private final TicketService ticketService;
    private final LocalMessageQueue<CreateTicketRequest> localMessageQueue;

    @Scheduled(cron = "*/3 * * * * *")
    public void ticketPopJob() {
        List<CreateTicketRequest> requests = new ArrayList<>();
        int queueSize = localMessageQueue.size();
        if (queueSize == 0) {
            return;
        }

        int max = 10;
        if (queueSize < max) {
            max = queueSize;
        }

        IntStream.range(0, max)
                .forEach(data -> requests.add(localMessageQueue.poll().message()));

        List<Ticket> tickets = ticketRepository.saveAll(Ticket.from(requests));
        tickets.forEach(ticketService::assignmentTicket);
    }

}
