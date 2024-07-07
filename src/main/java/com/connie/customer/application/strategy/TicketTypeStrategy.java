package com.connie.customer.application.strategy;

import com.connie.customer.api.dto.CreateTicketRequest;
import com.connie.customer.domain.enums.TicketType;

public interface TicketTypeStrategy {

    /**
     * 티켓 타입별 전략이 맞는지 확인하는 메소드
     * @param type 티켓 타입
     * @return boolean
     */
    boolean isValidStrategy(TicketType type);

    /**
     * 타입별 티켓 생성시 필요한 필드가 존재하는지 확인하는 메소드
     * @param request 요청 받은 필드
     */
    void checkTypeField(CreateTicketRequest request);

}
