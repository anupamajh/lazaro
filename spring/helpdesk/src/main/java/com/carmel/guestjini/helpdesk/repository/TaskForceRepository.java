package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskForce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskForceRepository extends JpaRepository<TaskForce, String> {
}
