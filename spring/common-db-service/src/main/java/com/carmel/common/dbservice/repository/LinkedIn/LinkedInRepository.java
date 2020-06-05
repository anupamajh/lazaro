package com.carmel.common.dbservice.repository.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkedInRepository extends JpaRepository<LinkedIn, String> {
}
