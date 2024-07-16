package com.connie.customer.application;

import com.connie.customer.api.dto.request.CreateTicketRequest;
import com.connie.customer.application.messagequeue.LocalMessageQueue;
import com.connie.customer.application.strategy.TicketTypeStrategy;
import com.connie.customer.domain.entity.TicketHandler;
import com.connie.customer.domain.entity.TicketHandlerIndex;
import com.connie.customer.domain.repository.TicketHandlerIndexRepository;
import com.connie.customer.domain.repository.TicketHandlerRepository;
import com.connie.customer.domain.repository.TicketMappingRepository;
import com.connie.customer.scheduler.TicketJob;
import com.connie.customer.util.FixtureUtils;
import com.navercorp.fixturemonkey.FixtureMonkey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@DisplayName("TicketService class 테스트")
class TicketServiceTest {

    @Autowired
    TicketHandlerRepository ticketHandlerRepository;

    @Autowired
    TicketMappingRepository ticketMappingRepository;

    @Autowired
    TicketHandlerIndexRepository ticketHandlerIndexRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    List<TicketTypeStrategy> ticketTypeStrategies;

    @Autowired
    TicketJob ticketJob;

    @Autowired
    ApplicationContext applicationContext;

    FixtureMonkey fixtureMonkey = FixtureUtils.getInstance();

    @Nested
    @DisplayName("티켓 생성 테스트")
    class CreateTicketTest {

        @Test
        @DisplayName("티켓 발행이 올바르게 발생한다.")
        void publishSuccessTest () {
            // given
            CreateTicketRequest request = fixtureMonkey.giveMeOne(CreateTicketRequest.class);

            // when
            boolean isPublished = ticketService.createTicket(request);

            // then
            assertThat(isPublished).isEqualTo(true);
        }

        @Test
        @DisplayName("발행된 메세지가 올바르게 소비된다.")
        void consumeTest () {
            // given
            CreateTicketRequest request = fixtureMonkey.giveMeBuilder(CreateTicketRequest.class)
                    .set("phoneNumber", "01012341234")
                    .sample();
            TicketHandler ticketHandler = TicketHandler.builder().id(1L).name("담당자1").build();
            TicketHandlerIndex ticketHandlerIndex = TicketHandlerIndex.builder().id(1L).handler(ticketHandler).build();
            ticketHandlerRepository.save(ticketHandler);
            ticketHandlerIndexRepository.save(ticketHandlerIndex);

            // when
            ticketService.createTicket(request);
            ticketJob.ticketPopJob();

            // then
            LocalMessageQueue<CreateTicketRequest> bean = applicationContext.getBean(LocalMessageQueue.class);
            assertThat(bean.size()).isEqualTo(0L);
        }

        @Test
        @DisplayName("다른 스레드로 발행되어도 올바르게 소비된다.")
        void differenceThreadTest() {
            // given

            // when

            // then
        }



    }

}
