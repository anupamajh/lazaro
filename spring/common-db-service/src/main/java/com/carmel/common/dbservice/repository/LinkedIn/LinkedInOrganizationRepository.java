package com.carmel.common.dbservice.repository.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkedInOrganizationRepository extends JpaRepository<LinkedInOrganization, String> {
}
