package com.connie.customer.domain.repository;

import com.connie.customer.domain.entity.TicketMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketMappingRepository extends JpaRepository<TicketMapping, Long> {
}
