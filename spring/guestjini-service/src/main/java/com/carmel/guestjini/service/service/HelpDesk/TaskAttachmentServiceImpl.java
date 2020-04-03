package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;
import com.carmel.guestjini.service.repository.HelpDesk.TaskAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAttachmentServiceImpl implements TaskAttachmentService {

    @Autowired
    TaskAttachmentRepository taskAttachmentRepository;

    @Override
    public TaskAttachment save(TaskAttachment taskAttachment) {
        return taskAttachmentRepository.save(taskAttachment);
    }

    @Override
    public List<TaskAttachment> findByTicketId(String ticketId) {
        return taskAttachmentRepository.findByTicketId(ticketId);
    }
}
