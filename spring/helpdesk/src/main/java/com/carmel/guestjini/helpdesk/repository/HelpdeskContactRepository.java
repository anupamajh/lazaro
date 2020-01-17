package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.HelpdeskContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpdeskContactRepository extends JpaRepository<HelpdeskContact, String> {
}
