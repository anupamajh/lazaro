package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskForceRepository extends JpaRepository<TaskForce, String> {
}
