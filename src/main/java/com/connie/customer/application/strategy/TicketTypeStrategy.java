package com.connie.customer.application.strategy;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.domain.enums.TicketType;

public interface TicketTypeStrategy {

    boolean isValidStrategy(TicketType type);

    void checkTypeField(CreateTicketRequest request);

}
