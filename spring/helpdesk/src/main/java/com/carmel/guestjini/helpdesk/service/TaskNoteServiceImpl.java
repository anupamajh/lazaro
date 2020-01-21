package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskNote;
import com.carmel.guestjini.helpdesk.repository.TaskNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskNoteServiceImpl implements TaskNoteService {

    @Autowired
    TaskNoteRepository taskNoteRepository;

    @Override
    public TaskNote save(TaskNote taskNote) {
        return taskNoteRepository.save(taskNote);
    }

    @Override
    public List<TaskNote> findByTicketIdAndIsDeleted(String ticketId, int isDeleted) {
        return taskNoteRepository.findByTicketIdAndIsDeleted(ticketId, isDeleted);
    }
}
