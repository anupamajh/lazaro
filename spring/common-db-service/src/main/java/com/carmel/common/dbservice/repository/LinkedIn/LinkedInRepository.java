package com.carmel.common.dbservice.repository.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkedInRepository extends JpaRepository<LinkedIn, String> {
    Optional<LinkedIn> findByLinkedInId(String linkedInId);
}
