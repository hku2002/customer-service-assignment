package com.connie.customer.domain.entity;

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
public class TicketHandlerIndex {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(cascade = ALL, fetch = LAZY)
    @PrimaryKeyJoinColumn
    private TicketHandler handler;

    @Builder
    public TicketHandlerIndex(Long id, TicketHandler handler) {
        this.id = id;
        this.handler = handler;
    }
}
