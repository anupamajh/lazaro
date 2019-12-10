package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    Organization save(Organization organization);

    List<Organization> findAllByOrgName(String orgName);

    List<Organization> findAllByOrgNameAndIdIsNot(String orgName, String id);

    Optional<Organization> findById(String id);

    List<Organization> findAllByDeletionStatus(int isDeleted, Client client);

    Page<Organization> findAll(Pageable pageable);

    Page<Organization> findAllByClient(Client client, Pageable pageable);

    Page<Organization> findAll(Specification<Organization> textInAllColumns, Pageable pageable);
}
