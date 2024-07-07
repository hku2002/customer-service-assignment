package com.connie.customer.domain.repository;

import com.connie.customer.domain.entity.TicketHandlerIndex;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface TicketHandlerIndexRepository extends JpaRepository<TicketHandlerIndex, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TicketHandlerIndex> findById(Long id);

}
