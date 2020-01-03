package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTicketRepository extends JpaRepository<TaskTicket, String > {
    List<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String requesterId);

    Page<TaskTicket> findAll(Specification<TaskTicket> textInAllColumns, Pageable pageable);

    Page<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String id, Pageable pageable);
}
