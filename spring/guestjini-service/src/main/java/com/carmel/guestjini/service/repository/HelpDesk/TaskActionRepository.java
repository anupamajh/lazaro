package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskActionRepository extends JpaRepository<TaskAction, String> {
}
