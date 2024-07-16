package com.connie.customer.application.strategy;

import com.connie.customer.api.dto.request.CreateTicketRequest;
import com.connie.customer.domain.enums.TicketType;
import org.springframework.stereotype.Component;

import static com.connie.customer.domain.enums.TicketType.FEEDBACK;

@Component
public class FeedbackTypeStrategy implements TicketTypeStrategy {
    @Override
    public boolean isValidStrategy(TicketType type) {
        return FEEDBACK.equals(type);
    }

    @Override
    public void checkTypeField(CreateTicketRequest request) {
        if (!FEEDBACK.equals(request.type())) {
            throw new IllegalArgumentException("피드백 문의 타입이 아닙니다.");
        }
    }
}
