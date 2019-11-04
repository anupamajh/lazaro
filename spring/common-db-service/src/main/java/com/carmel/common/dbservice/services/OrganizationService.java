package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    Organization save(Organization organization);

    List<Organization> findAllByOrgName(String orgName);

    List<Organization> findAllByOrgNameAndIdIsNot(String orgName, String id);

    Optional<Organization> findById(String id);

    List<Organization> findAllByDeletionStatus(int isDeleted);

    Page<Organization> findAll(Pageable pageable);

    Page<Organization> findAllByOrgNameContaining(String orgName, Pageable pageable);

    Page<Organization> findAllByDescriptionContaining(String description, Pageable pageable);

    Page<Organization> findAllByOrgNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String orgName, String description, Pageable pageable);
}
