package com.connie.customer.application.dto;

import com.connie.customer.application.messagequeue.enums.Topic;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record LocalMessageQueueDto<T>(
        UUID id,
        Topic topic,
        T message,
        LocalDateTime createdAt
) {

    public static <T> LocalMessageQueueDto<T> of(Topic topic, T message) {
        return new LocalMessageQueueDtoBuilder<T>()
                .id(UUID.randomUUID())
                .topic(topic)
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
