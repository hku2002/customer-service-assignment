package com.connie.customer.application.messagequeue.enums;

import lombok.Getter;

@Getter
public enum Topic {

    TICKET("티켓");

    private final String description;

    Topic(String description) {
        this.description = description;
    }
}
