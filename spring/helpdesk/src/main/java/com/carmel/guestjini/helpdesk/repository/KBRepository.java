package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.KB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KBRepository extends JpaRepository<KB, String> {
}
