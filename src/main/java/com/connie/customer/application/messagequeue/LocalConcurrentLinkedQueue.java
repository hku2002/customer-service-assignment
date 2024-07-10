package com.connie.customer.application.messagequeue;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 의존성 주입을 위한 class
 * @param <E>
 */
@Component
public class LocalConcurrentLinkedQueue<E> extends ConcurrentLinkedQueue<E> {
}
