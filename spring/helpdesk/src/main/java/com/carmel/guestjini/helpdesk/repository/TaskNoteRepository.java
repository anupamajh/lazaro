package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskNoteRepository extends JpaRepository<TaskNote, String> {
}
