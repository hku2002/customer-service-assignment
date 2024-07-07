package com.connie.customer.domain.entity;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.common.entity.BaseTimeEntity;
import com.connie.customer.domain.enums.TicketStatus;
import com.connie.customer.domain.enums.TicketType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import static com.connie.customer.domain.enums.TicketStatus.CREATED;
import static com.connie.customer.domain.enums.TicketType.GENERAL_INQUIRY;
import static com.connie.customer.domain.enums.TicketType.PROBLEM_INQUIRY;
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
        checkType(request);
        checkGeneralTypeField(request);
        checkProblemTypeField(request);
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

    private static void checkType(CreateTicketRequest request) {
        if (!hasDefaultField(request)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean hasDefaultField(CreateTicketRequest request) {
        return request.title() != null
                && request.contents() != null
                && request.email() != null;
    }

    private static void checkProblemTypeField(CreateTicketRequest request) {
        if (PROBLEM_INQUIRY.equals(request.type())) {
            throw new IllegalArgumentException("문제점 문의 타입이 아닙니다.");
        }
        if (StringUtils.hasText(request.phoneNumber()) && request.userId() != null) {
            throw new IllegalArgumentException("문제점 문의 타입의 필수값이 없습니다.");
        }
    }

    private static void checkGeneralTypeField(CreateTicketRequest request) {
        if (GENERAL_INQUIRY.equals(request.type())) {
            throw new IllegalArgumentException("일반 문의 타입이 아닙니다.");
        }
        if (request.userId() != null) {
            throw new IllegalArgumentException("일반 문의 타입의 필수값이 없습니다.");
        }
    }
}
