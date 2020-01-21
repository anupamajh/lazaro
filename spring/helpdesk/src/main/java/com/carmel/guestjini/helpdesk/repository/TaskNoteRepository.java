package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskNoteRepository extends JpaRepository<TaskNote, String> {
    List<TaskNote> findByTicketIdAndIsDeleted(String ticketId, int isDeleted);
}
