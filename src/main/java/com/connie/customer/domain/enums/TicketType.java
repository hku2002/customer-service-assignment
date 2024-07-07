package com.connie.customer.domain.enums;

import lombok.Getter;

@Getter
public enum TicketType {

    PROBLEM_INQUIRY("문제점 문의"),
    GENERAL_INQUIRY("일반 문의"),
    FEEDBACK("피드백");

    private final String description;

    TicketType(String description) {
        this.description = description;
    }
}
