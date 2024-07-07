package com.connie.customer.domain.repository;

import com.connie.customer.domain.entity.TicketHandler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHandlerRepository extends JpaRepository<TicketHandler, Long> {
}
