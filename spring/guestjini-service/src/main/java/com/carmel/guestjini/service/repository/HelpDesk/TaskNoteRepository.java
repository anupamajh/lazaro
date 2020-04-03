package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskNoteRepository extends JpaRepository<TaskNote, String> {
    List<TaskNote> findByTicketIdAndIsDeleted(String ticketId, int isDeleted);
}
