package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskNote;

import java.util.List;

public interface TaskNoteService {
    TaskNote save(TaskNote taskNote);

    List<TaskNote> findByTicketIdAndIsDeleted(String ticketId, int isDeleted);
}
