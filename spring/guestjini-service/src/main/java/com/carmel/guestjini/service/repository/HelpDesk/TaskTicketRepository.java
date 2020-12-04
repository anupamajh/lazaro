package com.carmel.guestjini.service.repository.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTicketRepository extends JpaRepository<TaskTicket, String > {
    List<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String requesterId);

    Page<TaskTicket> findAll(Specification<TaskTicket> textInAllColumns, Pageable pageable);

    Page<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String id, Pageable pageable);

    List<TaskTicket> findAllByIsDeletedAndRequesterIdAndTicketStatus(int isDeleted, String requesterId, int status);

    Page<TaskTicket> findAllByIsDeleted(int isDeleted, Pageable pageable);

    List<TaskTicket> findAllByIsDeletedAndTicketStatusIsNot(int isDeleted, int ticketStatus);

    List<TaskTicket> findAllByIsDeletedAndTicketStatus(int isDeleted, int ticketStatus);
}
