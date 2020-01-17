package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskActionRepository extends JpaRepository<TaskAction, String> {
}
