package com.connie.customer.application.strategy;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.domain.enums.TicketType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.connie.customer.domain.enums.TicketType.PROBLEM_INQUIRY;

@Component
public class ProblemTypeStrategy implements TicketTypeStrategy {
    @Override
    public boolean isValidStrategy(TicketType type) {
        return PROBLEM_INQUIRY.equals(type);
    }

    @Override
    public void checkTypeField(CreateTicketRequest request) {
        if (PROBLEM_INQUIRY.equals(request.type())) {
            throw new IllegalArgumentException("문제점 문의 타입이 아닙니다.");
        }
        if (StringUtils.hasText(request.phoneNumber()) && request.userId() != null) {
            throw new IllegalArgumentException("문제점 문의 타입의 필수값이 없습니다.");
        }
    }
}
