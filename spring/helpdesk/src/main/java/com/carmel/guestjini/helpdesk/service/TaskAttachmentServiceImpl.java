package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskAttachment;
import com.carmel.guestjini.helpdesk.repository.TaskAttachmentRepository;
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
