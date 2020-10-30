package com.carmel.guestjini.service.HelpDesk.TicketFeedback.Repository;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Model.TicketFeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketFeedBackRepository extends JpaRepository<TicketFeedBack, String> {
    Optional<TicketFeedBack> findByUserIdAndTicketId(String userId, String ticketId);

    List<TicketFeedBack> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TicketFeedBack> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);
}
