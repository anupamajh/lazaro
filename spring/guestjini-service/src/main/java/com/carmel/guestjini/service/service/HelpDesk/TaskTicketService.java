package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.DTO.HelpDesk.TicketCountDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TaskTicketService {
    TaskTicket save(TaskTicket taskTicket);

    Optional<TaskTicket> findById(String id);

    List<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String requesterId);

    Page<TaskTicket> findAll(Specification<TaskTicket> textInAllColumns, Pageable pageable);

    Page<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String id, Pageable pageable);

    TicketCountDTO getTicketCountByStatus(String requesterId) throws Exception;

    List<TaskTicket> findAllByIsDeletedAndRequesterIdAndTicketStatus(int isDeleted, String id, int ticketStatus);

    Page<TaskTicket> findAllByIsDeleted(int isDeleted, Pageable pageable);
}
