package com.connie.customer.application.Factory;

import com.connie.customer.application.strategy.TicketTypeStrategy;
import com.connie.customer.domain.enums.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketTypeFactory {

    private final List<TicketTypeStrategy> ticketTypeStrategies;

    public TicketTypeStrategy findStrategy(TicketType type) {
        return ticketTypeStrategies.stream()
                .filter(strategy -> strategy.isValidStrategy(type))
                .findFirst()
                .orElseThrow();
    }

}
