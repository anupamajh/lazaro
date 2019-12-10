package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    List<Organization> findAllByOrgNameAndIsDeletedIs(String orgName, int isDeleted);

    List<Organization> findAllByOrgNameAndIdIsNotAndIsDeletedIs(String orgName, String id, int isDeleted);

    Page<Organization> findAllByIsDeletedIs(int isDeleted, Pageable pageable);

    List<Organization> findAllByIsDeletedAndClient(int isDeleted, Client client);

    Page<Organization> findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable);

    Page<Organization> findAll(Specification<Organization> textInAllColumns, Pageable pageable);
}
