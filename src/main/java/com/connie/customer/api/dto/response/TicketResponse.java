package com.connie.customer.api.dto.response;

import com.connie.customer.domain.entity.Ticket;
import com.connie.customer.domain.enums.TicketStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record TicketResponse(
        Long id,
        String title,
        String contents,
        TicketStatus status // TODO description 으로 convert 로직 추가
) {

    public static TicketResponse from(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .contents(ticket.getContents())
                .status(ticket.getStatus())
                .build();
    }

    public static List<TicketResponse> from(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketResponse::from)
                .toList();
    }

}
