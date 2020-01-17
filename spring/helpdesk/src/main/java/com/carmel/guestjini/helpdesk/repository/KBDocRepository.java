package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.KBDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KBDocRepository  extends JpaRepository<KBDoc, String> {
}
