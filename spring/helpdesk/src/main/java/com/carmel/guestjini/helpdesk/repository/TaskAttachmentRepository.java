package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, String> {
    List<TaskAttachment> findByTicketId(String ticketId);
}
