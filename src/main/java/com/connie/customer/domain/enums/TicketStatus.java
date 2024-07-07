package com.connie.customer.domain.enums;

import lombok.Getter;

@Getter
public enum TicketStatus {

    CREATED("새티켓 생성"),
    PROCESSING("진행중"),
    COMPLETED("완료");

    private final String description;

    TicketStatus(String description) {
        this.description = description;
    }
}
