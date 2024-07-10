package com.connie.customer.api.dto.request;

public record ModifyTicketRequest(

        Long ticketId,
        Long userId,
        String title,
        String contents
) {
}
