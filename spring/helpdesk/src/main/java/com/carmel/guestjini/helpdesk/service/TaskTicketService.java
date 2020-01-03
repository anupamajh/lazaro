package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskTicket;
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
}
