package com.carmel.guestjini.service.repository.Accounts;

import com.carmel.guestjini.service.model.Accounts.AccountTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AccountTicketRepository extends JpaRepository<AccountTicket, String> {
    List<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId);

    Page<AccountTicket> findAllByIsDeletedAndBookingId(int isDeleted, String bookingId, Pageable pageable);

    Page<AccountTicket> findAll(Specification<AccountTicket> textInAllColumns, Pageable pageable);

    List<AccountTicket> findAllByGuestId(String guestId);

    List<AccountTicket> findAllByGuestIdAndTicketIdentifierAndTicketStatus(String id, int transactionIdentifier, int ticketStatus);

    List<AccountTicket> findAllByParentId(String id);

    List<AccountTicket> findAllByGuestIdAndPeriodFromAndPeriodUptoAndTicketIdentifierAndTicketStatus(
            String guestId,
            Date periodFrom,
            Date periodUpto,
            int ticketIdentifier,
            int ticketStatus
    );


}
