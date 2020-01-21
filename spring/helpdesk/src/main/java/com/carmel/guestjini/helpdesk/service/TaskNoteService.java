package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskNote;

import java.util.List;

public interface TaskNoteService {
    TaskNote save(TaskNote taskNote);

    List<TaskNote> findByTicketIdAndIsDeleted(String ticketId, int isDeleted);
}
