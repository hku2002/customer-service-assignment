package com.connie.customer.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@DisplayName("TicketService class 테스트")
class TicketServiceTest {

    @Nested
    @DisplayName("티켓 생성 테스트")
    class CreateTicketTest {

    }

}
