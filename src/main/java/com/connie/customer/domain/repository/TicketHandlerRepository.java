package com.connie.customer.domain.repository;

import com.connie.customer.domain.entity.TicketHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketHandlerRepository extends JpaRepository<TicketHandler, Long> {

    @Query(
            "SELECT th " +
            "FROM TicketHandler th " +
            "LEFT JOIN TicketMapping tm ON th.id = tm.handler.id " +
            "GROUP BY th.id " +
            "ORDER BY COUNT(tm.id) ASC"
    )
    TicketHandler findFirstHandlerHasMinTicket();

}
