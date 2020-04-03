package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;

import java.util.List;

public interface TaskAttachmentService {
    TaskAttachment save(TaskAttachment taskAttachment);

    List<TaskAttachment> findByTicketId(String id);
}
