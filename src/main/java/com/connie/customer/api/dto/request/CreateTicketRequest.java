package com.connie.customer.api.dto.request;

import com.connie.customer.domain.enums.TicketType;

public record CreateTicketRequest(
        Long userId,
        String title,
        String contents,
        String phoneNumber,
        String email,
        TicketType type
) {
}
