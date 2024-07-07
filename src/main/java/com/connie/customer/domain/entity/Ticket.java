package com.connie.customer.domain.entity;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.common.entity.BaseTimeEntity;
import com.connie.customer.domain.enums.TicketStatus;
import com.connie.customer.domain.enums.TicketType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.connie.customer.domain.enums.TicketStatus.CREATED;
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
    private TicketType type;

    @Enumerated(STRING)
    @Column(nullable = false, length = 50)
    private TicketStatus status;

    @Builder
    public Ticket(Long id, Long userId, String title, String contents, String phoneNumber, String email, TicketType type, TicketStatus status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.status = status;
    }

    public static Ticket from(CreateTicketRequest request) {
        return Ticket.builder()
                .userId(request.userId())
                .title(request.title())
                .contents(request.contents())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .type(request.type())
                .status(CREATED)
                .build();
    }
}
