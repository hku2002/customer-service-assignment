package com.connie.customer.domain.entity;

import com.connie.customer.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    @ManyToOne(cascade = ALL, fetch = LAZY)
    private TicketHandler handlerId;

    @Column(nullable = false, unique = true)
    @OneToOne(cascade = ALL, fetch = LAZY)
    private Ticket ticketId;

}
