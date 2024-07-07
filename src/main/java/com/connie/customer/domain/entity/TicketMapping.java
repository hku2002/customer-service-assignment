package com.connie.customer.domain.entity;

import com.connie.customer.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class TicketMapping extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = ALL, fetch = LAZY)
    private TicketHandler handler;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = ALL, fetch = LAZY)
    private Ticket ticket;

    @Builder
    public TicketMapping(Long id, TicketHandler handler, Ticket ticket) {
        this.id = id;
        this.handler = handler;
        this.ticket = ticket;
    }

    public static TicketMapping of(TicketHandler ticketHandler, Ticket ticket) {
        return TicketMapping.builder()
                .handler(ticketHandler)
                .ticket(ticket)
                .build();
    }

}
