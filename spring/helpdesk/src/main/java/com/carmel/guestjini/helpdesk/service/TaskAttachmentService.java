package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskAttachment;

import java.util.List;

public interface TaskAttachmentService {
    TaskAttachment save(TaskAttachment taskAttachment);

    List<TaskAttachment> findByTicketId(String id);
}
