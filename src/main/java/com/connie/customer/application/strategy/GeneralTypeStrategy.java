package com.connie.customer.application.strategy;

import com.connie.customer.api.dto.request.CreateTicketRequest;
import com.connie.customer.domain.enums.TicketType;
import org.springframework.stereotype.Component;

import static com.connie.customer.domain.enums.TicketType.GENERAL_INQUIRY;

@Component
public class GeneralTypeStrategy implements TicketTypeStrategy {
    @Override
    public boolean isValidStrategy(TicketType type) {
        return GENERAL_INQUIRY.equals(type);
    }

    @Override
    public void checkTypeField(CreateTicketRequest request) {
        if (!GENERAL_INQUIRY.equals(request.type())) {
            throw new IllegalArgumentException("일반 문의 타입이 아닙니다.");
        }
        if (request.userId() == null) {
            throw new IllegalArgumentException("일반 문의 타입의 필수값이 없습니다.");
        }
    }
}
