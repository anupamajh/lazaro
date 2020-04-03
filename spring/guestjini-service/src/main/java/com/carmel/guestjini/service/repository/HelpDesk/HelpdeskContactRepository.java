package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.HelpdeskContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpdeskContactRepository extends JpaRepository<HelpdeskContact, String> {
}
