package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.HelpdeskDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpdeskDepartmentRepository extends JpaRepository<HelpdeskDepartment, String> {
}
