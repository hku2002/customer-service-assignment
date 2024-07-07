package com.connie.customer.domain.entity;

import com.connie.customer.common.entity.BaseTimeEntity;
import com.connie.customer.domain.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Ticket extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Enumerated(STRING)
    @Column(nullable = false, length = 50)
    private TicketStatus status;

}
