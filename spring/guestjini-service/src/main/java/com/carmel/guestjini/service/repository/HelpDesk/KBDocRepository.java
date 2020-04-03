package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KBDoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KBDocRepository  extends JpaRepository<KBDoc, String> {
}
