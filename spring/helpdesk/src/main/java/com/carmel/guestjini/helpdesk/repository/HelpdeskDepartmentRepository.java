package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.HelpdeskDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpdeskDepartmentRepository extends JpaRepository<HelpdeskDepartment, String> {
}
