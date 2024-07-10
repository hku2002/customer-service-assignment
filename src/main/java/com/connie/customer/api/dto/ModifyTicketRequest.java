package com.connie.customer.api.dto;

public record ModifyTicketRequest(

        Long ticketId,
        Long userId,
        String title,
        String contents
) {
}
