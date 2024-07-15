package com.connie.customer.application.messagequeue;

import com.connie.customer.application.dto.LocalMessageQueueDto;
import com.connie.customer.application.messagequeue.enums.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalMessageQueue<T> {

    private final LocalConcurrentLinkedQueue<LocalMessageQueueDto<T>> queue;

    public boolean add(T message) {
        if (message == null) {
            throw new IllegalArgumentException("메세지 정보가 존재하지 않습니다.");
        }
        return queue.add(LocalMessageQueueDto.of(Topic.TICKET, message));
    }

    public LocalMessageQueueDto<T> poll() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

}
