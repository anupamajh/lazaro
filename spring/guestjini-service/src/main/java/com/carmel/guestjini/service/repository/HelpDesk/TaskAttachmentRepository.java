package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, String> {
    List<TaskAttachment> findByTicketId(String ticketId);
}
